package com.buylife.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 积分记录表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("point_records")
@Schema(name = "PointRecords", description = "积分记录表")
public class PointRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "积分记录的唯一标识", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【积分记录的唯一标识】不能为空")
    private Integer id;

    @Schema(description = "关联用户表的外键", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【关联用户表的外键】不能为空")
    private Integer userId;

    @Schema(description = "积分的增加或减少数", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【积分的增加或减少数】不能为空")
    private Integer changePoints;

    @Schema(description = "变动后用户的总积分", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【变动后用户的总积分】不能为空")
    private Integer currentPoints;

    @Schema(description = "积分变动的原因描述", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "【积分变动的原因描述】不能为空")
    @Size(max = 255, message = "【积分变动的原因描述】长度不能超过255个字符")
    private String changeReason;

    @Schema(description = "积分变动的时间戳", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【积分变动的时间戳】不能为空")
    private Date changeTime;

    @Schema(description = "数据创建的时间戳", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【数据创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳", requiredMode = RequiredMode.REQUIRED)
    @NotNull(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

}
