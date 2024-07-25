package com.buylife.service;

import com.buylife.entity.Users;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
public interface UsersService extends IService<Users> {

    Page<Users> findPage(Users params);

    List<Users> findList(Users params);

    Users findById(Long id);

    boolean insert(Users users);

    boolean update(Users users);

    int delete(Long id);

}