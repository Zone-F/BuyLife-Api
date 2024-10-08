package com.buylife.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buylife.security.entity.CreateUserDTO;
import com.buylife.security.entity.User;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getEmail, email)
        );
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
        );
    }

    public boolean insert(CreateUserDTO createUserDTO) {

        User user = new User();
        BeanUtils.copyProperties(createUserDTO, user);

        return save(user);
    }
}
