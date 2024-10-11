package com.agileboot.admin.controller.buylife;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.domain.buylife.checkIn.CheckInApplicationService;
import com.agileboot.domain.buylife.checkIn.dto.TaskDTO;
import com.agileboot.domain.buylife.checkIn.query.TaskQuery;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.notice.command.NoticeAddCommand;
import com.agileboot.domain.system.notice.command.NoticeUpdateCommand;
import com.agileboot.domain.system.notice.dto.NoticeDTO;
import com.agileboot.domain.system.notice.query.NoticeQuery;
import com.agileboot.infrastructure.annotations.unrepeatable.Unrepeatable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Tag(name = "打卡任务API", description = "打卡任务相关的增删查改")
@RestController
@RequestMapping("/check/task")
@Validated
@RequiredArgsConstructor
public class CheckInTaskController extends BaseController {
    private final CheckInApplicationService checkInApplicationService;

    /**
     * 获取通知公告列表
     */
    @Operation(summary = "公告列表")
    @GetMapping
    public ResponseDTO<PageDTO<TaskDTO>> list(TaskQuery query) {
        PageDTO<TaskDTO> pageDTO = checkInApplicationService.getTaskList(query);
        return ResponseDTO.ok(pageDTO);
    }

//    /**
//     * 根据通知公告编号获取详细信息
//     */
//    @Operation(summary = "公告详情")
//    @PreAuthorize("@permission.has('system:notice:query')")
//    @GetMapping(value = "/{noticeId}")
//    public ResponseDTO<NoticeDTO> getInfo(@PathVariable @NotNull @Positive Long noticeId) {
//        return ResponseDTO.ok(noticeApplicationService.getNoticeInfo(noticeId));
//    }
//
//    /**
//     * 新增通知公告
//     */
//    @Operation(summary = "添加公告")
//    @Unrepeatable(interval = 60, checkType = Unrepeatable.CheckType.SYSTEM_USER)
//    @PreAuthorize("@permission.has('system:notice:add')")
//    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.ADD)
//    @PostMapping
//    public ResponseDTO<Void> add(@RequestBody NoticeAddCommand addCommand) {
//        noticeApplicationService.addNotice(addCommand);
//        return ResponseDTO.ok();
//    }
//
//    /**
//     * 修改通知公告
//     */
//    @Operation(summary = "修改公告")
//    @PreAuthorize("@permission.has('system:notice:edit')")
//    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.MODIFY)
//    @PutMapping("/{noticeId}")
//    public ResponseDTO<Void> edit(@PathVariable Long noticeId, @RequestBody NoticeUpdateCommand updateCommand) {
//        updateCommand.setNoticeId(noticeId);
//        noticeApplicationService.updateNotice(updateCommand);
//        return ResponseDTO.ok();
//    }
//
//    /**
//     * 删除通知公告
//     */
//    @Operation(summary = "删除公告")
//    @PreAuthorize("@permission.has('system:notice:remove')")
//    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.DELETE)
//    @DeleteMapping
//    public ResponseDTO<Void> remove(@RequestParam List<Integer> noticeIds) {
//        noticeApplicationService.deleteNotice(new BulkOperationCommand<>(noticeIds));
//        return ResponseDTO.ok();
//    }

}
