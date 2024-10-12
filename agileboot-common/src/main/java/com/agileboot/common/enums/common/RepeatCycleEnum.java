package com.agileboot.common.enums.common;

import com.agileboot.common.enums.DictionaryEnum;
import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;

@Dictionary(name = "tCheckInTasks.repeatCycle")
public enum RepeatCycleEnum implements DictionaryEnum<Integer> {
    /**
     * 通知类型
     */
     DAILY(1,"日",CssTag.SUCCESS),
     WEEKLY(2,"周",CssTag.SUCCESS);

    private final int value;
    private final String description;
    private final String cssTag;

    RepeatCycleEnum(int value, String description, String cssTag) {
        this.value = value;
        this.description = description;
        this.cssTag = cssTag;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String cssTag() {
        return cssTag;
    }
}
