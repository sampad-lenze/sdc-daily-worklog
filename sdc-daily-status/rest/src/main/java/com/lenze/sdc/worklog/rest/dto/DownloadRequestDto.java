package com.lenze.sdc.worklog.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DownloadRequestDto {
	@NotBlank
	private String userName;
	@NotBlank
	private LocalDate startDate;
	@NotBlank
	private LocalDate endDate;
}
