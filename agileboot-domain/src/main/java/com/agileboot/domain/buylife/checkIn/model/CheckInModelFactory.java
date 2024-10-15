package com.agileboot.domain.buylife.checkIn.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.buylife.checkIn.db.CheckInEntity;
import com.agileboot.domain.buylife.checkIn.db.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckInModelFactory {
    private final CheckInService checkInService;
    public CheckInModel loadById(Long id) {
        CheckInEntity byId = checkInService.getById(id);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, id, "打卡任务");
        }

        return new CheckInModel(byId, checkInService);
    }

    public CheckInModel create() {
        return new CheckInModel(checkInService);
    }
}