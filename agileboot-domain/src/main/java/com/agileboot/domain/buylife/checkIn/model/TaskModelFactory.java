package com.agileboot.domain.buylife.checkIn.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.buylife.checkIn.db.TaskEntity;
import com.agileboot.domain.buylife.checkIn.db.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskModelFactory {
    private final TaskService taskService;

    public TaskModel loadById(Long id) {
        TaskEntity byId = taskService.getById(id);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, id, "通知公告");
        }

        return new TaskModel(byId);
    }

    public TaskModel create() {
        return new TaskModel();
    }
}
