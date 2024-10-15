package com.agileboot.domain.buylife.checkIn.dto;

import cn.hutool.core.date.DateTime;
import com.agileboot.domain.buylife.checkIn.db.CheckInEntity;
import lombok.Data;

import java.math.BigInteger;

@Data
public class CheckInDTO {
    public CheckInDTO(CheckInEntity entity) {
        if (entity != null) {
            this.id = entity.getId() + "";
        }
    }
    private String id;

    private BigInteger taskId;

    private BigInteger userId;

}
