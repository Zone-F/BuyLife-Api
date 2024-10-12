package com.agileboot.admin.controller.buylife;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.domain.buylife.checkInTask.CheckInTaskApplicationService;
import com.agileboot.domain.buylife.checkInTask.command.TaskAddCommand;
import com.agileboot.domain.buylife.checkInTask.command.TaskUpdateCommand;
import com.agileboot.domain.buylife.checkInTask.dto.TaskDTO;
import com.agileboot.domain.buylife.checkInTask.query.TaskQuery;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.infrastructure.annotations.unrepeatable.Unrepeatable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "打卡任务API", description = "打卡任务相关的增删查改")
@RestController
@RequestMapping("/check/task")
@Validated
@RequiredArgsConstructor
public class CheckInController extends BaseController {
    private final CheckInTaskApplicationService checkInTaskApplicationService;

    /**
     * 获取通知公告列表
     */
    @Operation(summary = "公告列表")
    @GetMapping
    public ResponseDTO<PageDTO<TaskDTO>> list(TaskQuery query) {
        PageDTO<TaskDTO> pageDTO = checkInTaskApplicationService.getTaskList(query);
        return ResponseDTO.ok(pageDTO);
    }


    /**
     * 新增打卡任务
     */
    @Operation(summary = "添加打卡任务")
    @Unrepeatable(interval = 60, checkType = Unrepeatable.CheckType.SYSTEM_USER)
    @AccessLog(title = "打卡任务", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody TaskAddCommand addCommand) {
        checkInTaskApplicationService.addTask(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改打卡任务
     */
    @Operation(summary = "修改打卡任务")
    @AccessLog(title = "打卡任务", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{taskId}")
    public ResponseDTO<Void> edit(@PathVariable Long taskId, @RequestBody TaskUpdateCommand updateCommand) {
        updateCommand.setId(taskId);
        checkInTaskApplicationService.updateTask(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除打卡任务
     */
    @Operation(summary = "删除打卡任务")
    @AccessLog(title = "打卡任务", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam List<Integer> ids) {
        checkInTaskApplicationService.deleteTask(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

}
