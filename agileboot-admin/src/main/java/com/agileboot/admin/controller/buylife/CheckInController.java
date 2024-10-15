package com.agileboot.admin.controller.buylife;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.domain.buylife.checkIn.CheckInApplicationService;
import com.agileboot.domain.buylife.checkIn.command.CheckInAddCommand;
import com.agileboot.domain.buylife.checkIn.command.CheckInUpdateCommand;
import com.agileboot.domain.buylife.checkIn.dto.CheckInDTO;
import com.agileboot.domain.buylife.checkIn.query.CheckInQuery;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.infrastructure.annotations.unrepeatable.Unrepeatable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "打卡API", description = "打卡相关的增删查改")
@RestController
@RequestMapping("/check")
@Validated
@RequiredArgsConstructor
public class CheckInController extends BaseController {
    private final CheckInApplicationService checkInApplicationService;

    /**
     * 获取打卡记录列表
     */
    @Operation(summary = "打卡记录列表")
    @GetMapping
    public ResponseDTO<PageDTO<CheckInDTO>> list(CheckInQuery query) {
        PageDTO<CheckInDTO> pageDTO = checkInApplicationService.getCheckInList(query);
        return ResponseDTO.ok(pageDTO);
    }


    /**
     * 新增打卡任务
     */
    @Operation(summary = "打卡")
    @Unrepeatable(interval = 60, checkType = Unrepeatable.CheckType.SYSTEM_USER)
    @AccessLog(title = "打卡", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody CheckInAddCommand addCommand) {
        checkInApplicationService.addTask(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改打卡任务
     */
    @Operation(summary = "修改打卡任务")
    @AccessLog(title = "打卡任务", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{checkInId}")
    public ResponseDTO<Void> edit(@PathVariable Long checkInId, @RequestBody CheckInUpdateCommand updateCommand) {
        updateCommand.setId(checkInId);
        checkInApplicationService.updateTask(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除打卡任务
     */
    @Operation(summary = "删除打卡记录")
    @AccessLog(title = "打卡记录", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam List<Integer> ids) {
        checkInApplicationService.deleteTask(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

}
