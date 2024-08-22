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
public class CheckInDTO implements Serializable {
    @Schema(description = "关联打卡任务表的外键")
    @NotNull(message = "【关联打卡任务表的外键】不能为空")
    private Integer taskId;

    @Schema(description = "关联用户表的外键")
    @NotNull(message = "【关联用户表的外键】不能为空")
    private Integer userId;

    @Schema(description = "用户进行打卡的日期")
    @NotNull(message = "【用户进行打卡的日期】不能为空")
    private Date checkInTime;

    @Schema(description = "0：未打卡，1：已打卡")
    @NotNull(message = "【0：未打卡，1：已打卡】不能为空")
    private Integer status;

}
