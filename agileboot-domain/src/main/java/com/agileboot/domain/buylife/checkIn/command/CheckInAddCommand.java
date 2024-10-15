package com.agileboot.domain.buylife.checkIn.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
public class CheckInAddCommand {

    @NotBlank(message = "用户的id不能为空")
    protected BigInteger userId;

    @NotBlank(message = "任务的id不能为空")
    protected BigInteger taskId;


    protected Integer status;
}
