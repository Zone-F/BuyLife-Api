package com.buylife.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buylife.security.entity.CreateUserDTO;
import com.buylife.security.mapper.UserMapper;
import com.buylife.security.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buylife.user.pojo.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OR
 * @since 2023-06-03
 */
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

    private final UserMapper userMapper;
//    public String findByEmail(String email) {
//        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
//        Users user = new Users();  // 创建 Users 实例
//        user.setEmail(email);      // 设置 email 属性
//
//        return this.getOne(queryWrapper.eq("email", user.getEmail()));
//    }

    @Override
    public Users findByEmail(String email) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<Users>()
                        .eq(Users::getEmail, email)
        );
    }

    public boolean insert(CreateUserDTO createUserDTO) {

        Users user = new Users();
        BeanUtils.copyProperties(createUserDTO, user);

        return save(user);
    }
}
