package com.buylife.security.security.auth;

import com.buylife.security.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

  private final com.buylife.security.security.auth.AuthenticationService authenticationService;

  @PostMapping("/register")
  @Operation(summary = "注册")
  public Result register(@RequestBody com.buylife.security.security.auth.RegisterRequest request){
    return authenticationService.register(request);
  }

  @PostMapping("/authenticate")
  @Operation(summary = "认证")
  public Result authenticate(@RequestBody com.buylife.security.security.auth.AuthenticationRequest request){
    return authenticationService.authenticate(request);
  }

  @PostMapping("/refresh")
  @Operation(summary = "刷新 Token")
  public Result refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    return authenticationService.refreshToken(request, response);
  }


}
