package com.agileboot.domain.buylife.checkInTask.query;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TaskQuery extends AbstractPageQuery<TaskEntity> {

    private String creatorId;

    @Override
    public QueryWrapper<TaskEntity> addQueryCondition() {
        return new QueryWrapper<TaskEntity>()
                .eq("n.deleted", 0)
                .eq("n.creator_id", creatorId);
    }
}
