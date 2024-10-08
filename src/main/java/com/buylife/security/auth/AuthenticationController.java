package com.buylife.security.auth;

import com.buylife.common.ResponseResult;
import com.buylife.security.entity.CreateUserDTO;
import com.buylife.security.entity.LoginDTO;
import com.buylife.security.utils.Result;
import com.buylife.user.pojo.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  @Operation(summary = "注册")
  public ResponseResult<Boolean> register(@Valid @RequestBody CreateUserDTO user){
    return authenticationService.register(user);
  }

  @PostMapping("/authenticate")
  @Operation(summary = "认证")
  public Result authenticate(@Valid @RequestBody LoginDTO user){
    return authenticationService.authenticate(user);
  }

  @PostMapping("/refresh")
  @Operation(summary = "刷新 Token")
  public Result refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    return authenticationService.refreshToken(request, response);
  }


}
