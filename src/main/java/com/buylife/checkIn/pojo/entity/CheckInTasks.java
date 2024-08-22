package com.buylife.checkIn.pojo.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buylife.entity.base;
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
@TableName("check_in_tasks")
//@Schema(name = "CheckInTasks", description = "打卡任务表")
public class CheckInTasks extends base {

//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Schema(description = "唯一标识每个任务的自增主键")
//    @NotNull(message = "【唯一标识每个任务的自增主键】不能为空")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;

    private Integer userId;

    private String title;

    private String description;

    private Integer point;

    private Integer status;

    private Date startDate;

    private Integer repeatCycle;

//  private Date createTime;

    private Date updateTime;

}
