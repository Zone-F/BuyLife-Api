package com.buylife.checkIn.pojo.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buylife.entity.base;
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
@TableName("check_ins")
@Schema(name = "CheckIns", description = "打卡记录表")
public class CheckIns extends base {

//    @Serial
//    private static final long serialVersionUID=1L;
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;


    private Integer taskId;


    private Integer userId;


    private Date checkInTime;


    private Integer status;


//    private Date createTime;


    private Date updateTime;

    private Date expiryTime;

}
