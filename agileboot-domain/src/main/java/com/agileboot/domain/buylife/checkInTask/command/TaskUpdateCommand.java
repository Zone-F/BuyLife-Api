package com.agileboot.domain.buylife.checkInTask.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskUpdateCommand extends TaskAddCommand{
    @NotNull
    @Positive
    protected Long id;
}
