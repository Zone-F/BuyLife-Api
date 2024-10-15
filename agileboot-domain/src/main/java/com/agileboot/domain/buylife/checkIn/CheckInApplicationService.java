package com.agileboot.domain.buylife.checkIn;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.buylife.checkIn.command.CheckInAddCommand;
import com.agileboot.domain.buylife.checkIn.command.CheckInUpdateCommand;
import com.agileboot.domain.buylife.checkIn.db.CheckInEntity;
import com.agileboot.domain.buylife.checkIn.db.CheckInService;
import com.agileboot.domain.buylife.checkIn.dto.CheckInDTO;
import com.agileboot.domain.buylife.checkIn.model.CheckInModel;
import com.agileboot.domain.buylife.checkIn.model.CheckInModelFactory;
import com.agileboot.domain.buylife.checkIn.query.CheckInQuery;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckInApplicationService {
    private final CheckInService checkInService;

    private final CheckInModelFactory checkInModelFactory;

    public PageDTO<CheckInDTO> getCheckInList(CheckInQuery query) {
        Page<CheckInEntity> page = checkInService.getCheckInList(query.toPage(), query.toQueryWrapper());
        List<CheckInDTO> records = page.getRecords().stream().map(CheckInDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void addTask(CheckInAddCommand addCommand) {
        CheckInModel checkInModel = checkInModelFactory.create();
        checkInModel.loadAddCommand(addCommand);


        checkInModel.checkFields();
        checkInModel.checkIsChecked();

        checkInModel.insert();
    }

    public void updateTask(CheckInUpdateCommand updateCommand) {
        CheckInModel checkInModel = checkInModelFactory.loadById(updateCommand.getId());
        checkInModel.loadUpdateCommand(updateCommand);

        checkInModel.checkFields();

        checkInModel.updateById();
    }

    public void deleteTask(BulkOperationCommand<Integer> deleteCommand) {
        checkInService.removeBatchByIds(deleteCommand.getIds());
    }
}
