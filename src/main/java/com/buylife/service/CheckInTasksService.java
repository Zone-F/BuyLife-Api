package com.buylife.service;

import com.buylife.entity.CheckInTasks;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface CheckInTasksService extends IService<CheckInTasks> {

    Page<CheckInTasks> findPage(CheckInTasks params);

    List<CheckInTasks> findList(CheckInTasks params);

    CheckInTasks findById(Long id);

    boolean insert(CheckInTasks checkInTasks);

    boolean update(CheckInTasks checkInTasks);

    int delete(Long id);

}