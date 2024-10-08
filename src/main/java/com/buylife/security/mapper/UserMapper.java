package com.buylife.security.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buylife.security.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buylife.user.pojo.entity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author OR
 * @since 2023-06-03
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {

}
