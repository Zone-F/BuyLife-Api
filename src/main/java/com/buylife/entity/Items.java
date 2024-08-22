package com.buylife.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 商品表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("items")
@Schema(name = "Items", description = "商品表")
public class Items implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "商品ID")
    @NotNull(message = "【商品ID】不能为空")
    private Integer id;

    @Schema(description = "用户ID")
    @NotNull(message = "【用户ID】不能为空")
    private Integer userId;

    @Schema(description = "商品名称")
    @NotBlank(message = "【商品名称】不能为空")
    @Size(max = 100, message = "【商品名称】长度不能超过100个字符")
    private String name;

    @Schema(description = "商品描述")
    @NotBlank(message = "【商品描述】不能为空")
    @Size(max = 500, message = "【商品描述】长度不能超过500个字符")
    private String description;

    @Schema(description = "积分价格")
    @NotNull(message = "【积分价格】不能为空")
    private Integer price;

    @Schema(description = "库存数量")
    @NotNull(message = "【库存数量】不能为空")
    private Integer stock;

    @Schema(description = "数据创建的时间戳")
    @NotNull(message = "【数据创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳")
    @NotNull(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

}
