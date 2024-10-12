package com.agileboot.domain.buylife.checkInTask.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;
import com.agileboot.domain.buylife.checkInTask.db.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskModelFactory {
    private final TaskService taskService;

    public TaskModel loadById(Long id) {
        TaskEntity byId = taskService.getById(id);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, id, "打卡任务");
        }

        return new TaskModel(byId);
    }

    public TaskModel create() {
        return new TaskModel();
    }
}
