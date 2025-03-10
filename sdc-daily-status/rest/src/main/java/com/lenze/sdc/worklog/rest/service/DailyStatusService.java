package com.lenze.sdc.worklog.rest.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lenze.sdc.worklog.core.dao.service.UsersDaoService;
import com.lenze.sdc.worklog.core.exception.UserNotfoundException;
import com.lenze.sdc.worklog.core.exception.WorkLogNotFoundException;
import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.core.model.WorklogModel;
import com.lenze.sdc.worklog.core.service.WorklogCoreService;
import com.lenze.sdc.worklog.rest.dto.DailyStatusRequest;
import com.lenze.sdc.worklog.rest.dto.DailyStatusResponse;
import com.lenze.sdc.worklog.rest.mapper.WorkLogMapper;

import lombok.NonNull;

@Service
public class DailyStatusService {

	private WorklogCoreService worklogService;
	private UsersDaoService usersDaoService;

	DailyStatusService(WorklogCoreService worklogService, UsersDaoService usersDaoService) {
		this.worklogService = worklogService;
		this.usersDaoService = usersDaoService;
	}

	public String postDaily(DailyStatusRequest request) {
		UserModel findByUserName = usersDaoService.findByUserName(request.getUserName());
		if(Objects.isNull(findByUserName)) {
			throw new UserNotfoundException("User not found!! Please provide existing user or else sign up incase of new user.");
		}
		WorklogModel model = WorkLogMapper.mapRequestDtoToModel(request);
		// unique id for each entry
		model.setWorklogId(UUID.randomUUID().toString());
		return worklogService.postDaily(model) ? model.getWorklogId() : "";
	}

	public boolean updateWorklog(DailyStatusRequest request, String workLogId) {
		WorklogModel findById = worklogService.findById(workLogId);
		if (Objects.isNull(findById)) {
			throw new WorkLogNotFoundException("Worklog not found!! Please provide proper Id.");
		}
		WorklogModel model = WorkLogMapper.mapRequestDtoToModel(request);
		model.setWorklogId(workLogId);
		return worklogService.updateDaily(model);
	}

	public List<DailyStatusResponse> findByUserName(String userName) {
		List<WorklogModel> allWorklog = worklogService.findByUserName(userName);
		return allWorklog.stream().map(WorkLogMapper::mapModelToResponse).toList();
	}

	public List<DailyStatusResponse> getAllWorklog() {
		List<WorklogModel> allWorklog = worklogService.getAllWorklog();
		return allWorklog.stream().map(WorkLogMapper::mapModelToResponse).toList();
	}

	public List<Object> getWorklogByUserNameFilterInput(@NonNull String userName, @NonNull String filterInput) {
		if ("leave".equalsIgnoreCase(filterInput)) {
			List<WorklogModel> list = worklogService.getWorklogByUserNameFilterInput(userName, filterInput);
			return list.stream().map(wm -> (Object) wm.getDate()).toList();
		} else if ("training".equalsIgnoreCase(filterInput)) {
			List<WorklogModel> list = worklogService.getWorklogByUserNameFilterInput(userName, filterInput);
			return list.stream().map(wm -> (Object) wm.getWorkDetails()).toList();
		}
		return Collections.emptyList();
	}

	public List<String> getWhoIsPending(String projectName, LocalDate today) {
		return worklogService.getPendingUsers(projectName, today);
	}

	public List<DailyStatusResponse> exportToExcel(String userName, LocalDate startDate, LocalDate endDate) {
		UserModel user = usersDaoService.findByUserName(userName.toLowerCase());
		List<WorklogModel> listOfLogs = worklogService.exportToExcel(user.getUserName(), startDate, endDate);
		return listOfLogs.stream().map(WorkLogMapper::mapModelToResponse).toList();
	}
}
