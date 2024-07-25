package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.PurchaseHistoryMapper;
import com.buylife.entity.PurchaseHistory;
import com.buylife.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@Service
@Slf4j
public class PurchaseHistoryServiceImpl extends ServiceImpl<PurchaseHistoryMapper, PurchaseHistory> implements PurchaseHistoryService {

    @Autowired
    private PurchaseHistoryMapper purchaseHistoryMapper;

    @Override
    public Page<PurchaseHistory> findPage(PurchaseHistory params) {
        Page<PurchaseHistory> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<PurchaseHistory> query = Wrappers.lambdaQuery(PurchaseHistory.class);
        return purchaseHistoryMapper.selectPage(page, query);
    }

    @Override
    public List<PurchaseHistory> findList(PurchaseHistory params){
        LambdaQueryWrapper<PurchaseHistory> query = Wrappers.lambdaQuery(PurchaseHistory.class);
        return purchaseHistoryMapper.selectList(query);
    }

    @Override
    public PurchaseHistory findById(Long id) {
        return purchaseHistoryMapper.selectById(id);
    }

    @Override
    public boolean insert(PurchaseHistory purchaseHistory) {
        return save(purchaseHistory);
    }

    @Override
    public boolean update(PurchaseHistory purchaseHistory) {
        return updateById(purchaseHistory);
    }

    @Override
    public int delete(Long id) {
        return purchaseHistoryMapper.deleteById(id);
    }

}