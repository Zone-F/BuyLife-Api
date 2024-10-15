package com.agileboot.domain.buylife.checkIn.db;

import cn.hutool.core.date.DateTime;
import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@TableName("t_check_ins")
@ApiModel(value = "CheckInEntity对象", description = "打卡记录表")
public class CheckInEntity extends BaseEntity<CheckInEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private BigInteger userId;

    @ApiModelProperty("打卡任务id")
    @TableField("task_id")
    private BigInteger taskId;

    @ApiModelProperty("0：未打卡，1：已打卡")
    private Integer status;
}
