package com.agileboot.domain.buylife.checkIn.query;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.buylife.checkIn.db.CheckInEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CheckInQuery extends AbstractPageQuery<CheckInEntity> {
    private String creatorId;

    @Override
    public QueryWrapper<CheckInEntity> addQueryCondition() {
        return new QueryWrapper<CheckInEntity>()
                .eq("n.deleted", 0)
                .eq("n.status", 1)
                .eq("n.creator_id", creatorId);
    }
}
