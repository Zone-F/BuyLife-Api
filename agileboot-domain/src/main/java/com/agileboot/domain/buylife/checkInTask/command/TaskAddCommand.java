package com.agileboot.domain.buylife.checkInTask.command;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TaskAddCommand {
    @NotBlank(message = "标题不能为空")
    @Size(max = 30, message = "标题不能超过30个字符")
    protected String title;

    @NotBlank(message = "任务的详细描述不能为空")
    @Size(max = 50, message = "标题不能超过50个字符")
    protected String description;

    @NotBlank(message = "任务积分不能为空")
    protected Integer point;

    @NotBlank(message = "任务状态不能为空")
    protected Integer status;

    @NotBlank(message = "任务开始执行的日期不能为空")
    protected Date startDate;

    @NotBlank(message = "任务重复的周期类型不能为空")
    protected Integer repeatCycle;
}
