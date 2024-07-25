package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.PointRecordsMapper;
import com.buylife.entity.PointRecords;
import com.buylife.service.PointRecordsService;
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
public class PointRecordsServiceImpl extends ServiceImpl<PointRecordsMapper, PointRecords> implements PointRecordsService {

    @Autowired
    private PointRecordsMapper pointRecordsMapper;

    @Override
    public Page<PointRecords> findPage(PointRecords params) {
        Page<PointRecords> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<PointRecords> query = Wrappers.lambdaQuery(PointRecords.class);
        return pointRecordsMapper.selectPage(page, query);
    }

    @Override
    public List<PointRecords> findList(PointRecords params){
        LambdaQueryWrapper<PointRecords> query = Wrappers.lambdaQuery(PointRecords.class);
        return pointRecordsMapper.selectList(query);
    }

    @Override
    public PointRecords findById(Long id) {
        return pointRecordsMapper.selectById(id);
    }

    @Override
    public boolean insert(PointRecords pointRecords) {
        return save(pointRecords);
    }

    @Override
    public boolean update(PointRecords pointRecords) {
        return updateById(pointRecords);
    }

    @Override
    public int delete(Long id) {
        return pointRecordsMapper.deleteById(id);
    }

}