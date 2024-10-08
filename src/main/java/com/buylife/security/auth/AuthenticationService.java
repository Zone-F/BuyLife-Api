package com.buylife.security.auth;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.buylife.common.ResponseResult;
import com.buylife.security.entity.CreateUserDTO;
import com.buylife.security.entity.LoginDTO;
import com.buylife.security.entity.User;
import com.buylife.security.entity.Token;
import com.buylife.security.enums.TokenType;
import com.buylife.security.config.JwtService;
import com.buylife.security.service.TokenService;
import com.buylife.security.service.UserService;
import com.buylife.security.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public ResponseResult<Boolean> register(CreateUserDTO request) {

        // 判断用户是否存在
        var oldUser = userService.findByEmail(request.getEmail());
        if (oldUser != null) {
            return ResponseResult.fail("用户名已存在");
        }
//      新增用户
        CreateUserDTO user = new CreateUserDTO();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.insert(user);
        return ResponseResult.success("注册成功！");
    }

    public Result authenticate(@Valid LoginDTO request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        if (userDetails == null || !passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            return Result.error("用户名或密码不正确！");
        }

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        // TODO 考虑不从数据库中查数据

        var user = (User) authenticate.getPrincipal();
        // var user = userService.findByEmail(request.getEmail());
        // var user = userDetailsService.loadUserByUsername(request.getEmail());


        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeUserToken(user.getId());
        saveUserToken(user.getId(), jwtToken);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        return Result.success("认证成功！", response);

    }

    public Result refreshToken(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.error("你的操作非法！");
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            // log.info("username: {}",username);
            // TODO 考虑不从数据库获取用户数据
            // var user = userService.findByEmail(username);
            var user = (User) userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                log.info("accessToken: {}", accessToken);
                revokeUserToken(user.getId());
                saveUserToken(user.getId(), accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                // new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                return Result.success("刷新成功！", authResponse);
            }
            return Result.error("令牌无效！");
        }
        return Result.error("获取用户名失败！");
    }


    /**
     * 保存用户的 token
     *
     * @param userId
     * @param jwtToken
     */
    private void saveUserToken(Long userId, String jwtToken) {
        var token = Token.builder()
                .userId(userId)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        LambdaUpdateWrapper<Token> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Token::getUserId, userId);
        tokenService.saveOrUpdate(token, lambdaUpdateWrapper);
    }


    /**
     * 销毁用户的 token
     * - expired = true
     * - revoked = true
     *
     * @param userId
     */
    private void revokeUserToken(Long userId) {
        var validUserToken = tokenService.findValidTokenByUserId(userId);
        if (validUserToken.isEmpty())
            return;
        validUserToken.ifPresent(token -> {
            token.setExpired(true);
            token.setRevoked(true);
            LambdaUpdateWrapper<Token> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Token::getUserId, userId);
            tokenService.update(token, lambdaUpdateWrapper);
        });

    }
}
