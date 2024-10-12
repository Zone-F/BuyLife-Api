package com.agileboot.domain.buylife.checkInTask.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface TaskService extends IService<TaskEntity> {
    /**
     * 获取打卡任务列表
     *
     * @param page         页码对象
     * @param queryWrapper 查询对象
     * @return 分页处理后的公告列表
     */
    Page<TaskEntity> getTaskList(Page<TaskEntity> page,
                                        @Param(Constants.WRAPPER) Wrapper<TaskEntity> queryWrapper);
}