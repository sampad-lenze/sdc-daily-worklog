package com.lenze.sdc.worklog.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRequestResponseDto {
	@NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String designation;
    @NotBlank
    private String gender;
    @NotBlank
    private String project;
    private long doneTicketCount;
    private long inProgressTicketCount;
    private double totalWorkingHours;
}
