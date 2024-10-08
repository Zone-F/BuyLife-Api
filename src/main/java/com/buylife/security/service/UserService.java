package com.buylife.security.service;

import com.buylife.security.entity.CreateUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buylife.security.entity.User;
import com.buylife.user.pojo.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-06-03
 */
public interface UserService extends IService<User> {

    User findByEmail(String email);

    User findByUsername(String username);

    boolean insert(CreateUserDTO user);

}
