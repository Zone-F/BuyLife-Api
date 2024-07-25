package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.ItemsMapper;
import com.buylife.entity.Items;
import com.buylife.service.ItemsService;
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
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public Page<Items> findPage(Items params) {
        Page<Items> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<Items> query = Wrappers.lambdaQuery(Items.class);
        return itemsMapper.selectPage(page, query);
    }

    @Override
    public List<Items> findList(Items params){
        LambdaQueryWrapper<Items> query = Wrappers.lambdaQuery(Items.class);
        return itemsMapper.selectList(query);
    }

    @Override
    public Items findById(Long id) {
        return itemsMapper.selectById(id);
    }

    @Override
    public boolean insert(Items items) {
        return save(items);
    }

    @Override
    public boolean update(Items items) {
        return updateById(items);
    }

    @Override
    public int delete(Long id) {
        return itemsMapper.deleteById(id);
    }

}