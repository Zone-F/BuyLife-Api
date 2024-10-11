package com.agileboot.domain.buylife.checkIn.command;

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
