package com.agileboot.domain.buylife.checkIn.query;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.buylife.checkIn.db.TaskEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TaskQuery extends AbstractPageQuery<TaskEntity> {

    private String userId;

    @Override
    public QueryWrapper<TaskEntity> addQueryCondition() {
        return new QueryWrapper<TaskEntity>()
                .eq("n.deleted", 0)
                .eq("n.user_id", userId);
    }
}
