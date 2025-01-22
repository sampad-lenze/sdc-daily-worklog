package com.lenze.sdc.worklog.rest.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.rest.dto.UserRequestResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

	
	public static UserModel mapRequestDtoToModel(@NonNull UserRequestResponseDto request) {
		LOGGER.info("Map dto to model {}", request);
		return UserModel.builder()
				.userId(request.getUserId())
				.userName(request.getUserName())
				.designation(request.getDesignation())
				.gender(request.getGender())
				.project(request.getProject())
				.build();
	}
	
	public static UserRequestResponseDto mapModelToResponse(@NonNull UserModel model) {
		LOGGER.info("Map model to response dto {}", model);
		return UserRequestResponseDto.builder()
				.userId(model.getUserId())
				.userName(model.getUserName())
				.designation(model.getDesignation())
				.gender(model.getGender())
				.project(model.getProject())
				.build();
	}
}
