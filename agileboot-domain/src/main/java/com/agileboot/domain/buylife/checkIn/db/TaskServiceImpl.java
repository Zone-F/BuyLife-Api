package com.agileboot.domain.buylife.checkIn.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService{
    @Override
    public Page<TaskEntity> getTaskList(Page<TaskEntity> page, Wrapper<TaskEntity> queryWrapper) {
        return this.baseMapper.getTaskList(page, queryWrapper);
    }
}
