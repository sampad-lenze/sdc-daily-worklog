package com.lenze.sdc.worklog.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.core.model.WorklogModel;
import com.lenze.sdc.worklog.core.service.WorklogCoreService;
import com.lenze.sdc.worklog.rest.dto.UserRequestResponseDto;
import com.lenze.sdc.worklog.rest.mapper.UserMapper;

@Service
@Qualifier("ManageUserService")
public class ManageUserService {

	private final WorklogCoreService worklogCoreService;
	ManageUserService(WorklogCoreService worklogCoreService){
		this.worklogCoreService = worklogCoreService;
	}

	public boolean registerUser(UserRequestResponseDto request) {
		UserModel model = UserMapper.mapRequestDtoToModel(request);
		worklogCoreService.registerUser(model);
		return true;
	}

	
	public boolean deleteUser(String id) {
		 worklogCoreService.deleteUser(id);
		 return true;
	}
	
	public List<UserRequestResponseDto> getAllUsers() {
		List<UserModel> users = worklogCoreService.findAllUsers();
		List<UserRequestResponseDto> response = new ArrayList<>();
		for (UserModel userModel : users) {
			List<WorklogModel> doneList = worklogCoreService.getWorklogByUserNameFilterInput(userModel.getUserName(),
					"done");
			List<WorklogModel> inProgList = worklogCoreService.getWorklogByUserNameFilterInput(userModel.getUserName(),
					"in progress");
			double totalWorkHoursOfCurrentYear = Stream.concat(doneList.stream(), inProgList.stream())
					.mapToDouble(WorklogModel::getWorkingHours).sum();

			UserRequestResponseDto responseDto = UserMapper.mapModelToResponse(userModel);
			if (!doneList.isEmpty() || !inProgList.isEmpty()) {
				responseDto.setDoneTicketCount(doneList.size());
				responseDto.setInProgressTicketCount(inProgList.size());
				responseDto.setTotalWorkingHours(totalWorkHoursOfCurrentYear);
			}
			response.add(responseDto);
		}
		return response;
	}
}
