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
	List<WorklogModel> findUsersWithEntriesForDate(String projectName, LocalDate date);
	List<WorklogModel> findByNameAndDateRange(String userName, LocalDate startDate, LocalDate endDate);
	WorklogModel findByNameAndDate(String userName, LocalDate date);
}
