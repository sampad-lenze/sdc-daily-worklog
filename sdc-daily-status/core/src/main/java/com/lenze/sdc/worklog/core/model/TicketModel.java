package com.lenze.sdc.worklog.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@SuperBuilder
public class TicketModel {
    private long doneCount;
    private long inProgressCount;
    private double totalWorkingHours;
}
