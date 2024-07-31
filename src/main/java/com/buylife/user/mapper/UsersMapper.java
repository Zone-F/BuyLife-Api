package com.buylife.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buylife.user.pojo.entity.Users;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户信息表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 *
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}