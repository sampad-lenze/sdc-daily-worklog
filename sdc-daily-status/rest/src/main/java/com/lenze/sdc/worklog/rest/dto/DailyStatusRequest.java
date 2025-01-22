package com.lenze.sdc.worklog.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@Schema(name = "DailyStatusRequest")
public class DailyStatusRequest {
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
