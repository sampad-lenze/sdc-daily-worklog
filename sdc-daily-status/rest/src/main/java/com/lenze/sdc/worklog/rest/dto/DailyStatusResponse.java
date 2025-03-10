package com.lenze.sdc.worklog.rest.dto;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    
    public List<String> getValues() {
        return List.of(
            String.valueOf(weekNumber),
            workDetails,
            date.toString(),
            projectName,
            userName,
            String.valueOf(workingHours),
            status);
    }
    
    public static List<String> getFieldNames() {
        return Arrays.stream(DailyStatusResponse.class.getDeclaredFields())
                .map(Field::getName)
                .filter(fieldName -> !fieldName.equals("id"))
                .toList();
    }
}
