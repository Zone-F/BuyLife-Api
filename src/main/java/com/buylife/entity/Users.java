package com.buylife.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户信息表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("users")
@Schema(name = "Users", description = "用户信息表")
public class Users implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识每个用户的自增主键")
    @NotNull(message = "【唯一标识每个用户的自增主键】不能为空")
    private Integer id;

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

    @Schema(description = "用户创建的时间戳")
    @NotNull(message = "【用户创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳")
    @NotNull(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

}
