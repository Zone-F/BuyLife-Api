package com.buylife.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.buylife.security.entity.Permission.*;
import static com.buylife.security.entity.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final com.buylife.security.security.config.JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;
  private final com.buylife.security.security.config.RestAuthorizationEntryPoint restAuthorizationEntryPoint;
  private final com.buylife.security.security.config.RestfulAccessDeniedHandler restfulAccessDeniedHandler;

  @Bean
  // 添加这一行来指定扫描路径
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(
                "/api/v1/auth/**",
                "/api/v1/test/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/doc.html",
                "/html-doc.html",
                "/webjars/**",
                "/swagger-ui.html",
                "/favicon.ico"
            ).permitAll()
            .requestMatchers("/api/v1/supervisor/**").hasAnyRole(SUPERVISOR.name(), ADMIN.name())

            .requestMatchers(GET, "/api/v1/supervisor/**").hasAnyAuthority(SUPERVISOR_READ.name(), ADMIN_READ.name())
            .requestMatchers(POST, "/api/v1/supervisor/**").hasAnyAuthority(SUPERVISOR_CREATE.name(), ADMIN_CREATE.name())
            .requestMatchers(PUT, "/api/v1/supervisor/**").hasAnyAuthority(SUPERVISOR_UPDATE.name(), ADMIN_UPDATE.name())
            .requestMatchers(DELETE, "/api/v1/supervisor/**").hasAnyAuthority(SUPERVISOR_DELETE.name(), ADMIN_DELETE.name())

            .requestMatchers("/api/v1/chaser/**").hasRole(CHASER.name())

            .requestMatchers(GET, "/api/v1/chaser/**").hasAuthority(CHASER_READ.name())
            .requestMatchers(POST, "/api/v1/chaser/**").hasAuthority(CHASER_CREATE.name())
            .requestMatchers(PUT, "/api/v1/chaser/**").hasAuthority(CHASER_UPDATE.name())
            .requestMatchers(DELETE, "/api/v1/chaser/**").hasAuthority(CHASER_DELETE.name())

            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            //添加jwt 登录授权过滤器
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout()
            .logoutUrl("/api/v1/auth/logout")
            .addLogoutHandler(logoutHandler)
            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())

    ;
    //添加自定义未授权和未登录结果返回
    http.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)
            .authenticationEntryPoint(restAuthorizationEntryPoint);

    return http.build();
  }
}
