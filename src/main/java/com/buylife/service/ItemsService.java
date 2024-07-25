package com.buylife.service;

import com.buylife.entity.Items;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface ItemsService extends IService<Items> {

    Page<Items> findPage(Items params);

    List<Items> findList(Items params);

    Items findById(Long id);

    boolean insert(Items items);

    boolean update(Items items);

    int delete(Long id);

}