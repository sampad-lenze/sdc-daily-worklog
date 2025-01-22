package com.lenze.sdc.worklog.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lenze.sdc.worklog.core.dao.service.DailyStatusDaoService;
import com.lenze.sdc.worklog.core.dao.service.UsersDaoService;
import com.lenze.sdc.worklog.core.exception.UserNotfoundException;
import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.core.model.WorklogModel;

@Service
@Qualifier("WorklogCoreService")
public class WorklogCoreService {

	private DailyStatusDaoService statusDaoService;
	private UsersDaoService usersDaoService;

	WorklogCoreService(DailyStatusDaoService statusDaoService, UsersDaoService usersDaoService) {
		this.statusDaoService = statusDaoService;
		this.usersDaoService = usersDaoService;
	}

	public boolean postDaily(WorklogModel worklogModel) {
		statusDaoService.postDailyStatus(worklogModel);
		return true;
	}

	public boolean updateDaily(WorklogModel worklogModel) {
		statusDaoService.updateDailyStatus(worklogModel);
		return true;
	}

	public List<WorklogModel> getAllWorklog() {
		return statusDaoService.fetchAllWorklog();
	}

	public List<WorklogModel> findByUserName(String userName) {
		return statusDaoService.fetchByName(userName);
	}

	public WorklogModel findById(String worklogId) {
		return statusDaoService.fetchById(worklogId);
	}

	public void registerUser(UserModel model) {
		usersDaoService.registerUser(model);
	}

	public void deleteUser(String id) {
		usersDaoService.deleteUser(id);
	}

	public List<UserModel> findAllUsers() {
		return usersDaoService.findAllUsers();
	}

	public List<WorklogModel> getWorklogByUserNameFilterInput(String userName, String filterInput) {
		UserModel user = usersDaoService.findByUserName(userName.toLowerCase());
		if (Objects.isNull(user)) {
			throw new UserNotfoundException("User not found!! Please provide proper Id.");
		}
		return statusDaoService.getWorklogByUserNameFilterInput(userName.toLowerCase(), filterInput.toLowerCase());
	}
	   
	public List<String> getWhoIsPending(String projectName, LocalDate today) {
		List<String> allUsers = usersDaoService.findAllUsersByProject(projectName).stream().map(u -> u.getUserName()).toList();
		List<String> usersWithEntries = statusDaoService.findUsersWithEntriesForDate(projectName, today);
		return allUsers.stream().filter(user -> !usersWithEntries.contains(user)).toList();
	}
}
