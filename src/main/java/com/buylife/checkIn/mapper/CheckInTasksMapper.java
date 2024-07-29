package com.buylife.checkIn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buylife.checkIn.pojo.entity.CheckInTasks;
import org.apache.ibatis.annotations.Mapper;
/**
 * 打卡任务表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 *
 */
@Mapper
public interface CheckInTasksMapper extends BaseMapper<CheckInTasks> {

}