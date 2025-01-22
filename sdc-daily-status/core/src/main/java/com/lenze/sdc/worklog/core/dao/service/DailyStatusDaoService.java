package com.lenze.sdc.worklog.core.dao.service;

import java.time.LocalDate;
import java.util.List;

import com.lenze.sdc.worklog.core.model.WorklogModel;

public interface DailyStatusDaoService {
	
	List<WorklogModel> fetchAllWorklog();
	WorklogModel fetchById(String worklogId);
	List<WorklogModel> fetchByName(String userName);
	void postDailyStatus(WorklogModel model);
	void updateDailyStatus(WorklogModel model);
	void deleteDailyStatusByUserName(String userName);
	List<WorklogModel> getWorklogByUserNameFilterInput(String userId, String filterInput);
	List<String> findUsersWithEntriesForDate(String projectName, LocalDate date);
}
