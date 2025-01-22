package com.lenze.sdc.worklog.rest.mapper;

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

//	public static WorklogEntity mapModelToEntity(WorklogModel worklogModel) {
//		LOGGER.info("Map model to entity {}", worklogModel);
//		return WorklogEntity.builder()
//				.weekNumber(worklogModel.getWeekNumber())
//				.date(worklogModel.getDate())
//				.projectName(worklogModel.getProjectName())
//				.workDetails(worklogModel.getWorkDetails())
//				.workingHours(worklogModel.getWorkingHours())
//				.userName(worklogModel.getUserName())
//				.status(worklogModel.getStatus())
//				.build();
//	}

//	public static WorklogModel mapEntityToModel(WorklogEntity worklogEntity) {
//		LOGGER.info("Map entity to model {}", worklogEntity);
//		return WorklogModel.builder()
//				.id(worklogEntity.getId())
//				.weekNumber(worklogEntity.getWeekNumber())
//				.date(worklogEntity.getDate())
//				.projectName(worklogEntity.getProjectName())
//				.workDetails(worklogEntity.getWorkDetails())
//				.workingHours(worklogEntity.getWorkingHours())
//				.status(worklogEntity.getStatus())
//				.userName(worklogEntity.getUserName())
//				.build();
//	}
	
	public static WorklogModel mapRequestDtoToModel(DailyStatusRequest request) {
		LOGGER.info("Map dto to model {}", request);
		return WorklogModel.builder()
				.weekNumber(request.getWeekNumber())
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
