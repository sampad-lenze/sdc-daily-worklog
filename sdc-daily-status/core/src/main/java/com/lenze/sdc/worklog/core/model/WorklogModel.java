package com.lenze.sdc.worklog.core.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@SuperBuilder
public class WorklogModel {
	private String worklogId;
	private long weekNumber;
    private String workDetails;
    @NonNull
    private LocalDate date;
    @NonNull
    private String projectName;
    @NonNull
    private String userName;
    private double workingHours;
    private String status;
}
