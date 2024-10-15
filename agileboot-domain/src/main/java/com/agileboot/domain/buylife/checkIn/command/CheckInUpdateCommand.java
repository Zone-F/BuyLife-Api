package com.agileboot.domain.buylife.checkIn.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckInUpdateCommand extends CheckInAddCommand{
    @NotNull
    @Positive
    protected Long id;
}
