package com.agileboot.domain.buylife.checkIn.db;

import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CheckInMapper extends BaseMapper<CheckInEntity> {
    /**
     * 根据条件分页查询角色关联的用户列表
     *
     * @param page         分页对象
     * @param queryWrapper 条件选择器
     * @return 分页处理后的打卡列表
     */
    @Select("SELECT n.* "
            + "FROM t_check_ins n "
            + "LEFT JOIN sys_user u ON n.creator_id = u.user_id"
            + " ${ew.customSqlSegment}")
    Page<CheckInEntity> getCheckInList(Page<CheckInEntity> page,
                                 @Param(Constants.WRAPPER) Wrapper<CheckInEntity> queryWrapper);
}
