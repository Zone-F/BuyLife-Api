package com.buylife.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户购买历史表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Data
@TableName("purchase_history")
@Schema(name = "PurchaseHistory", description = "用户购买历史表")
public class PurchaseHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "购买记录ID")
    @NotNull(message = "【购买记录ID】不能为空")
    private Integer id;

    @Schema(description = "用户ID")
    @NotNull(message = "【用户ID】不能为空")
    private Integer userId;

    @Schema(description = "商品ID")
    @NotNull(message = "【商品ID】不能为空")
    private Integer itemId;

    @Schema(description = "购买数量")
    @NotNull(message = "【购买数量】不能为空")
    private Integer quantity;

    @Schema(description = "购买时间")
    @NotNull(message = "【购买时间】不能为空")
    private Date purchasedAt;

    @Schema(description = "数据创建的时间戳")
    @NotNull(message = "【数据创建的时间戳】不能为空")
    private Date createTime;

    @Schema(description = "数据更新的时间戳")
    @NotNull(message = "【数据更新的时间戳】不能为空")
    private Date updateTime;

}
