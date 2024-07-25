package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.CheckInsMapper;
import com.buylife.entity.CheckIns;
import com.buylife.service.CheckInsService;
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
public class CheckInsServiceImpl extends ServiceImpl<CheckInsMapper, CheckIns> implements CheckInsService {

    @Autowired
    private CheckInsMapper checkInsMapper;

    @Override
    public Page<CheckIns> findPage(CheckIns params) {
        Page<CheckIns> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<CheckIns> query = Wrappers.lambdaQuery(CheckIns.class);
        return checkInsMapper.selectPage(page, query);
    }

    @Override
    public List<CheckIns> findList(CheckIns params){
        LambdaQueryWrapper<CheckIns> query = Wrappers.lambdaQuery(CheckIns.class);
        return checkInsMapper.selectList(query);
    }

    @Override
    public CheckIns findById(Long id) {
        return checkInsMapper.selectById(id);
    }

    @Override
    public boolean insert(CheckIns checkIns) {
        return save(checkIns);
    }

    @Override
    public boolean update(CheckIns checkIns) {
        return updateById(checkIns);
    }

    @Override
    public int delete(Long id) {
        return checkInsMapper.deleteById(id);
    }

}