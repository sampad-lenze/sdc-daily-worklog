package com.lenze.sdc.worklog.rest.mapper;

import java.time.temporal.WeekFields;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lenze.sdc.worklog.core.model.WorklogModel;
import com.lenze.sdc.worklog.rest.dto.DailyStatusRequest;
import com.lenze.sdc.worklog.rest.dto.DailyStatusResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkLogMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkLogMapper.class);
	
	public static WorklogModel mapRequestDtoToModel(DailyStatusRequest request) {
		LOGGER.info("Map dto to model {}", request);
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
	    int weekNumber = request.getDate().get(weekFields.weekOfWeekBasedYear());
		return WorklogModel.builder()
				.weekNumber(weekNumber)
				.date(request.getDate())
				.projectName(request.getProjectName())
				.workDetails(request.getWorkDetails())
				.workingHours(request.getWorkingHours())
				.status(request.getStatus())
				.userName(request.getUserName())
				.build();
	}
	
	public static DailyStatusResponse mapModelToResponse(WorklogModel model) {
		LOGGER.info("Map model to response dto {}", model);
		return DailyStatusResponse.builder()
				.id(model.getWorklogId())
				.weekNumber(model.getWeekNumber())
				.date(model.getDate())
				.projectName(model.getProjectName())
				.workDetails(model.getWorkDetails())
				.workingHours(model.getWorkingHours())
				.status(model.getStatus())
				.userName(model.getUserName())
				.build();
	}
}
