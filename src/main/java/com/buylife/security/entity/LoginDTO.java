package com.buylife.security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    @Schema(description = "用户的昵称或姓名，用于登录和展示")
    @NotNull(message = "【用户名】不能为空")
    private String username;

    @Schema(description = "用户密码，需经过加密处理")
    @NotNull(message = "【用户密码】不能为空")
    private String password;
}
