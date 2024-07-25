package com.buylife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buylife.entity.PurchaseHistory;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户购买历史表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 *
 */
@Mapper
public interface PurchaseHistoryMapper extends BaseMapper<PurchaseHistory> {

}