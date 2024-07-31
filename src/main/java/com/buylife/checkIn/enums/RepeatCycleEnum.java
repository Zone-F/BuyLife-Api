package com.buylife.checkIn.enums;

import lombok.Getter;

/**
 * 打卡周期类型枚举
 */
@Getter
public enum RepeatCycleEnum {
    /**
     * 每天
     */
    DAILY(1, "daily"),

    /**
     * 每周
     */
    WEEKLY(2, "weekly"),

    /**
     * 每月
     */
    MONTHLY(3, "monthly"),

    /**
     * 每年
     */
    YEARLY(4, "yearly");

    private final int code;
    private final String value;

    RepeatCycleEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
