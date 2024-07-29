package com.buylife.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 打卡任务表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("check_in_tasks")
@Schema(name = "CheckInTasks", description = "打卡任务表")
public class CheckInTasks implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识每个任务的自增主键")
    @NotNull(message = "【唯一标识每个任务的自增主键】不能为空")
    private Integer id;

    @Schema(description = "与用户表相关联的外键，标识创建任务的用户")
    @NotNull(message = "【与用户表相关联的外键，标识创建任务的用户】不能为空")
    private Integer userId;

    @Schema(description = "用户为任务定义的标题")
    @NotBlank(message = "【用户为任务定义的标题】不能为空")
    private String title;

    @Schema(description = "任务的详细描述")
    @NotBlank(message = "【任务的详细描述】不能为空")
    private String description;

    @Schema(description = "任务积分")
    @NotNull(message = "【任务积分】不能为空")
    private Integer point;

    @Schema(description = "任务状态：1活跃，0关闭")
    @NotNull(message = "【任务状态：1活跃，0关闭】不能为空")
    private Integer status;

    @Schema(description = "任务开始执行的日期")
    @NotNull(message = "【任务开始执行的日期】不能为空")
    private Date startDate;

    @Schema(description = "任务重复的周期类型")
    @NotNull(message = "【任务重复的周期类型】不能为空")
    private List<String> repeatCycle; // 假设repeatCycle是字符串列表

    @Schema(description = "数据创建的时间戳")
    @NotNull(message = "【数据创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳")
    @NotNull(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

}
