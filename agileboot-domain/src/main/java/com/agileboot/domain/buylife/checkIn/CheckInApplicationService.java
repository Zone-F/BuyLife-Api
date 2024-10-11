package com.agileboot.domain.buylife.checkIn;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.buylife.checkIn.db.TaskEntity;
import com.agileboot.domain.buylife.checkIn.db.TaskService;
import com.agileboot.domain.buylife.checkIn.dto.TaskDTO;
import com.agileboot.domain.buylife.checkIn.model.TaskModelFactory;
import com.agileboot.domain.buylife.checkIn.query.TaskQuery;
import com.agileboot.domain.system.notice.dto.NoticeDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckInApplicationService {
    private final TaskService taskService;

    private  final TaskModelFactory taskModelFactory;

    public PageDTO<TaskDTO> getTaskList(TaskQuery query) {
        Page<TaskEntity> page = taskService.getTaskList(query.toPage(), query.toQueryWrapper());
        List<TaskDTO> records = page.getRecords().stream().map(TaskDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

}
