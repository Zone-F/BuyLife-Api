package com.agileboot.domain.buylife.checkInTask;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.buylife.checkInTask.command.TaskAddCommand;
import com.agileboot.domain.buylife.checkInTask.command.TaskUpdateCommand;
import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;
import com.agileboot.domain.buylife.checkInTask.db.TaskService;
import com.agileboot.domain.buylife.checkInTask.dto.TaskDTO;
import com.agileboot.domain.buylife.checkInTask.model.TaskModel;
import com.agileboot.domain.buylife.checkInTask.model.TaskModelFactory;
import com.agileboot.domain.buylife.checkInTask.query.TaskQuery;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckInTaskApplicationService {
    private final TaskService taskService;

    private  final TaskModelFactory taskModelFactory;

    public PageDTO<TaskDTO> getTaskList(TaskQuery query) {
        Page<TaskEntity> page = taskService.getTaskList(query.toPage(), query.toQueryWrapper());
        List<TaskDTO> records = page.getRecords().stream().map(TaskDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void addTask(TaskAddCommand addCommand) {
        TaskModel taskModel = taskModelFactory.create();

        taskModel.loadAddCommand(addCommand);

        taskModel.checkFields();

        taskModel.insert();
    }


    public void updateTask(TaskUpdateCommand updateCommand) {
        TaskModel taskModel = taskModelFactory.loadById(updateCommand.getId());
        taskModel.loadUpdateCommand(updateCommand);

        taskModel.checkFields();

        taskModel.updateById();
    }

    public void deleteTask(BulkOperationCommand<Integer> deleteCommand) {
        taskService.removeBatchByIds(deleteCommand.getIds());
    }
}
