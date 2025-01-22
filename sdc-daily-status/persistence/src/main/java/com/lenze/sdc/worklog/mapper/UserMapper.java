package com.lenze.sdc.worklog.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.persistence.UserEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

	public static UserEntity mapModelToEntity(@NonNull UserModel userModel) {
		LOGGER.info("Map model to entity {}", userModel);
		 // Trim the string and convert to lowercase
        String userName = userModel.getUserName().trim().toLowerCase();
        // Replace spaces between surname and middle name with underscores
        userName = userName.replaceAll("\\s+", "_");
		return new UserEntity(userModel.getUserId(), userName,
				userModel.getGender(), userModel.getDesignation(), userModel.getProject());
	}

	public static UserModel mapEntityToModel(@NonNull UserEntity userEntity) {
		LOGGER.info("Map entity to model {}", userEntity);
		return UserModel.builder()
				.userId(userEntity.getUserId())
				.userName(userEntity.getUserName().toLowerCase())
				.designation(userEntity.getUserDesignation())
				.gender(userEntity.getUserGender())
				.project(userEntity.getProject())
				.build();
	}
}
