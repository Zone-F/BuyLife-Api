package com.agileboot.domain.buylife.checkIn.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.domain.buylife.checkIn.command.TaskAddCommand;
import com.agileboot.domain.buylife.checkIn.command.TaskUpdateCommand;
import com.agileboot.domain.buylife.checkIn.db.TaskEntity;
import com.agileboot.domain.system.notice.command.NoticeAddCommand;
import com.agileboot.domain.system.notice.command.NoticeUpdateCommand;
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
}
