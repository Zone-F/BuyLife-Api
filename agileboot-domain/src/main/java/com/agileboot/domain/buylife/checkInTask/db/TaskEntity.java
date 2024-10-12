package com.agileboot.domain.buylife.checkInTask.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("t_check_in_tasks")
@ApiModel(value = "TaskEntity对象", description = "打卡任务表")
public class TaskEntity extends BaseEntity<TaskEntity> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户为任务定义的标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("任务的详细描述")
    private String description;

    @ApiModelProperty("任务积分")
    private Integer point;

    @ApiModelProperty("任务状态：1活跃，0关闭")
    private Integer status;

    @ApiModelProperty("任务开始执行的日期")
    private Date startDate;

    @ApiModelProperty("任务重复的周期类型")
    private Integer repeatCycle;

    @ApiModelProperty("创建用户的id")
    @TableField(value = "user_id")
    private Integer userId;

}
