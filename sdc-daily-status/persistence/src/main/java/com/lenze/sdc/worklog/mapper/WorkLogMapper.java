package com.lenze.sdc.worklog.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lenze.sdc.worklog.core.model.WorklogModel;
import com.lenze.sdc.worklog.persistence.WorklogEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkLogMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkLogMapper.class);

	public static WorklogEntity mapModelToEntity(@NonNull WorklogModel worklogModel) {
		LOGGER.info("Map model to entity {}", worklogModel);
		return new WorklogEntity(worklogModel.getWorklogId(), worklogModel.getWeekNumber(),
				worklogModel.getWorkDetails(),worklogModel.getDate(),
				worklogModel.getUserName(), worklogModel.getProjectName(),
				worklogModel.getWorkingHours(), worklogModel.getStatus());
	}

	public static WorklogModel mapEntityToModel(@NonNull WorklogEntity worklogEntity) {
		LOGGER.info("Map entity to model {}", worklogEntity);
		return WorklogModel.builder()
				.worklogId(worklogEntity.getWorklogId())
				.weekNumber(worklogEntity.getWeekNumber())
				.date(worklogEntity.getDate())
				.projectName(worklogEntity.getProjectName())
				.workDetails(worklogEntity.getWorkDetails())
				.workingHours(worklogEntity.getWorkingHours())
				.status(worklogEntity.getStatus())
				.userName(worklogEntity.getUserName())
				.build();
	}
}
