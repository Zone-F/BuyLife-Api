package com.buylife.checkIn.service;

import com.buylife.checkIn.pojo.dto.CheckInDTO;
import com.buylife.checkIn.pojo.entity.CheckIns;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface CheckInsService extends IService<CheckIns> {

    Page<CheckIns> findPage(CheckIns params);

    List<CheckIns> findList(CheckIns params);

    CheckIns findById(Long id);

    boolean insert(CheckInDTO checkIns);

    boolean update(CheckIns checkIns);

    int delete(Long id);

}