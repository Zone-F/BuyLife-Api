package com.agileboot.domain.buylife.checkIn.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import java.math.BigInteger;

@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckInEntity> implements CheckInService {
    @Override
    public Page<CheckInEntity> getCheckInList(Page<CheckInEntity> page, Wrapper<CheckInEntity> queryWrapper) {
        return this.baseMapper.getCheckInList(page, queryWrapper);
    }

    @Override
    public boolean isChecked(BigInteger userId, BigInteger taskId) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 构建当天的开始时间和结束时间
        LocalDateTime startTime = currentDate.atStartOfDay();
        LocalDateTime endTime = currentDate.atTime(23, 59, 59); // 当天的最后一秒
        // 转换为 Timestamp 类型
        Timestamp startTimestamp = Timestamp.valueOf(startTime);
        Timestamp endTimestamp = Timestamp.valueOf(endTime);


        QueryWrapper<CheckInEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("task_id",taskId)
                .eq("status", 1)
                .between("create_time", startTimestamp, endTimestamp);
        return baseMapper.exists(queryWrapper);
    }
}
