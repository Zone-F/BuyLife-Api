package com.buylife.service;

import com.buylife.entity.PurchaseHistory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface PurchaseHistoryService extends IService<PurchaseHistory> {

    Page<PurchaseHistory> findPage(PurchaseHistory params);

    List<PurchaseHistory> findList(PurchaseHistory params);

    PurchaseHistory findById(Long id);

    boolean insert(PurchaseHistory purchaseHistory);

    boolean update(PurchaseHistory purchaseHistory);

    int delete(Long id);

}