package com.buylife.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 打卡记录表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("check_ins")
@Schema(name = "CheckIns", description = "打卡记录表")
public class CheckIns implements Serializable{

    @Serial
    private static final long serialVersionUID=1L;

    @Schema(description = "打卡记录的唯一标识")
    @NotNull(message = "【打卡记录的唯一标识】不能为空")
    private Integer id;

    @Schema(description = "关联打卡任务表的外键")
    @NotBlank(message = "【关联打卡任务表的外键】不能为空")
    private Integer taskId;

    @Schema(description = "关联用户表的外键")
    @NotBlank(message = "【关联用户表的外键】不能为空")
    private Integer userId;

    @Schema(description = "用户进行打卡的日期")
    @NotBlank(message = "【用户进行打卡的日期】不能为空")
    private Date checkInTime;

    @Schema(description = "0：未打卡，1：已打卡")
    @NotBlank(message = "【0：未打卡，1：已打卡】不能为空")
    private Integer status;

    @Schema(description = "数据创建的时间戳")
    @NotBlank(message = "【数据创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳")
    @NotBlank(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

    @Schema(description = "打卡任务的过期时间")
    @NotBlank(message = "【打卡任务的过期时间】不能为空")
    private Date expiryTime;

}
