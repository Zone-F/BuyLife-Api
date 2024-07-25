package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.CheckInTasksMapper;
import com.buylife.entity.CheckInTasks;
import com.buylife.service.CheckInTasksService;
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
public class CheckInTasksServiceImpl extends ServiceImpl<CheckInTasksMapper, CheckInTasks> implements CheckInTasksService {

    @Autowired
    private CheckInTasksMapper checkInTasksMapper;

    @Override
    public Page<CheckInTasks> findPage(CheckInTasks params) {
        Page<CheckInTasks> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<CheckInTasks> query = Wrappers.lambdaQuery(CheckInTasks.class);
        return checkInTasksMapper.selectPage(page, query);
    }

    @Override
    public List<CheckInTasks> findList(CheckInTasks params){
        LambdaQueryWrapper<CheckInTasks> query = Wrappers.lambdaQuery(CheckInTasks.class);
        return checkInTasksMapper.selectList(query);
    }

    @Override
    public CheckInTasks findById(Long id) {
        return checkInTasksMapper.selectById(id);
    }

    @Override
    public boolean insert(CheckInTasks checkInTasks) {
        return save(checkInTasks);
    }

    @Override
    public boolean update(CheckInTasks checkInTasks) {
        return updateById(checkInTasks);
    }

    @Override
    public int delete(Long id) {
        return checkInTasksMapper.deleteById(id);
    }

}