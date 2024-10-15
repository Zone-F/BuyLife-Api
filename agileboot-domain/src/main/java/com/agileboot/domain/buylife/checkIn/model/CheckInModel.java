package com.agileboot.domain.buylife.checkIn.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.buylife.checkIn.command.CheckInAddCommand;
import com.agileboot.domain.buylife.checkIn.command.CheckInUpdateCommand;
import com.agileboot.domain.buylife.checkIn.db.CheckInEntity;
import com.agileboot.domain.buylife.checkIn.db.CheckInService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CheckInModel extends CheckInEntity {
    private CheckInService checkInService;

    public CheckInModel(CheckInEntity entity, CheckInService checkInService) {
        this(checkInService);

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public CheckInModel(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    public void loadAddCommand(CheckInAddCommand command) {
        if (command != null) {
            command.setStatus(1);
            BeanUtil.copyProperties(command, this, "id");
        }
    }

    public void checkIsChecked() {
        if (checkInService.isChecked(getUserId(), getTaskId())) {
            throw new ApiException(ErrorCode.Business.BUYLIFE_CHECKIN_CHECKED_TODAY);
        }
    }

    public void loadUpdateCommand(CheckInUpdateCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkFields() {

        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }
}
