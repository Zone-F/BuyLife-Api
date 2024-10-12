package com.agileboot.domain.buylife.checkInTask.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.common.enums.common.RepeatCycleEnum;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.domain.buylife.checkInTask.command.TaskAddCommand;
import com.agileboot.domain.buylife.checkInTask.command.TaskUpdateCommand;
import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TaskModel extends TaskEntity {
    public TaskModel(TaskEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadAddCommand(TaskAddCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "id");
        }
    }

    public void loadUpdateCommand(TaskUpdateCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkFields() {
        BasicEnumUtil.fromValue(RepeatCycleEnum.class, getRepeatCycle());

        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }
}
