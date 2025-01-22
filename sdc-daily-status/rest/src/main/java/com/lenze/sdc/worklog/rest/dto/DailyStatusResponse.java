package com.lenze.sdc.worklog.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@SuperBuilder
//@Schema(name = "DailyStatusResponse")
public class DailyStatusResponse {
	private String id;
	private long weekNumber;
    private String workDetails;
    @NotBlank
    private LocalDate date;
    @NotBlank
    private String projectName;
    @NotBlank
    private String userName;
    private double workingHours;
    private String status;
}
