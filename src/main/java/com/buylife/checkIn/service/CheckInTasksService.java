package com.buylife.checkIn.service;

import com.buylife.checkIn.pojo.dto.CreateCheckInTasksDTO;
import com.buylife.checkIn.pojo.entity.CheckInTasks;
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

    boolean insert(CreateCheckInTasksDTO checkInTasks);

    boolean update(CheckInTasks checkInTasks);

    int delete(Long id);

}