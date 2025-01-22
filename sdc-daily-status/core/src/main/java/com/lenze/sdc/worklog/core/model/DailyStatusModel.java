package com.lenze.sdc.worklog.core.model;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder
public class DailyStatusModel {
	private long weekNumber;
    private String workDetails;
    private LocalDate date;
    private String projectName;
    private String userName;
    private double workingHours;
    private String status;
}
