package com.buylife.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buylife.common.exception.CustomException;
import com.buylife.user.mapper.UsersMapper;
import com.buylife.user.pojo.entity.Users;
import com.buylife.user.service.UsersService;
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
    // 使用LambdaQueryWrapper查询数据库中是否已存在相同的用户名
//    LambdaQueryWrapper<Users> queryWrapper = Wrappers.lambdaQuery();
//    queryWrapper.eq(Users::getUsername, users.getUsername());
//
//    // 执行查询，如果结果不为空，说明用户名已存在
//    Users existingUser = this.getOne(queryWrapper);
//    if (existingUser != null) {
//        // 可以在这里抛出异常，或者直接返回false表示插入失败
//        throw new CustomException(200,"用户名已存在");
//    }
//
//    // 如果用户名不存在，则继续执行插入操作
//    return save(users);
    return true ;
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