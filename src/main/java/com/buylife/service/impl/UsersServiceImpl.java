package com.buylife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.mapper.UsersMapper;
import com.buylife.entity.Users;
import com.buylife.service.UsersService;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Page<Users> findPage(Users params) {
        Page<Users> page = new Page<>(1, 10);//TODO 自行处理
        LambdaQueryWrapper<Users> query = Wrappers.lambdaQuery(Users.class);
        return usersMapper.selectPage(page, query);
    }

    @Override
    public List<Users> findList(Users params){
        LambdaQueryWrapper<Users> query = Wrappers.lambdaQuery(Users.class);
        return usersMapper.selectList(query);
    }

    @Override
    public Users findById(Long id) {
        return usersMapper.selectById(id);
    }

    @Override
    public boolean insert(Users users) {
        return save(users);
    }

    @Override
    public boolean update(Users users) {
        return updateById(users);
    }

    @Override
    public int delete(Long id) {
        return usersMapper.deleteById(id);
    }

}