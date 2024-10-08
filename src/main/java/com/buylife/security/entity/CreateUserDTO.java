package com.buylife.security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Data
public class CreateUserDTO implements Serializable{
    @Schema(description = "用户的昵称或姓名，用于登录和展示")
    @NotBlank(message = "【用户的昵称或姓名，用于登录和展示】不能为空")
    @Size(max = 50, message = "【用户名】长度不能超过50个字符")
    private String username;

    @Schema(description = "用户的邮箱地址，用于注册和通知")
    @NotBlank(message = "【用户的邮箱地址，用于注册和通知】不能为空")
    @Email(message = "【用户的邮箱地址】必须是有效的电子邮件地址")
    @Size(max = 254, message = "【用户的邮箱地址】长度不能超过254个字符")
    private String email;

    @Schema(description = "用户密码，需经过加密处理")
    @NotNull(message = "【用户密码，需经过加密处理】不能为空")
    @Size(min = 8, max = 128, message = "【用户密码】长度应在8到128个字符之间")
    private String password;
}
