package com.buylife.checkIn.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreateCheckInTasksDTO implements Serializable {

    @Schema(description = "与用户表相关联的外键，标识创建任务的用户")
    @NotNull(message = "【与用户表相关联的外键，标识创建任务的用户】不能为空")
    private Integer userId;

    @Schema(description = "用户为任务定义的标题")
    @NotBlank(message = "【用户为任务定义的标题】不能为空")
    private String title;

    @Schema(description = "任务的详细描述")
    private String description;

    @Schema(description = "任务积分")
    @NotNull(message = "【任务积分】不能为空")
    private Integer point;

    @Schema(description = "任务状态：1活跃，0关闭")
    private Integer status;

    @Schema(description = "任务开始执行的日期")
    @NotNull(message = "【任务开始执行的日期】不能为空")
    private Date startDate;

    @Schema(description = "任务重复的周期类型")
    @NotNull(message = "【任务重复的周期类型】不能为空")
    private Integer repeatCycle;
}