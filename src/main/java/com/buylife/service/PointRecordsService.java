package com.buylife.service;

import com.buylife.entity.PointRecords;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface PointRecordsService extends IService<PointRecords> {

    Page<PointRecords> findPage(PointRecords params);

    List<PointRecords> findList(PointRecords params);

    PointRecords findById(Long id);

    boolean insert(PointRecords pointRecords);

    boolean update(PointRecords pointRecords);

    int delete(Long id);

}