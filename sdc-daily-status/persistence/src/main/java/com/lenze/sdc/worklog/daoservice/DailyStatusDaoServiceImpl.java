package com.lenze.sdc.worklog.daoservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenze.sdc.worklog.core.dao.service.DailyStatusDaoService;
import com.lenze.sdc.worklog.core.exception.WorkLogNotFoundException;
import com.lenze.sdc.worklog.core.model.WorklogModel;
import com.lenze.sdc.worklog.mapper.WorkLogMapper;
import com.lenze.sdc.worklog.persistence.WorklogEntity;
import com.lenze.sdc.worklog.persistence.WorklogRepository;

@Service
@Transactional(readOnly = true)
public class DailyStatusDaoServiceImpl implements DailyStatusDaoService{

	private WorklogRepository worklogRepository;
	DailyStatusDaoServiceImpl(WorklogRepository worklogRepository){
		this.worklogRepository = worklogRepository;
	}

	@Override
	@Transactional
	public void postDailyStatus(WorklogModel worklog) {
		WorklogEntity entity = WorkLogMapper.mapModelToEntity(worklog);
		worklogRepository.save(entity);
	}
	
	@Override
	@Transactional
	public void updateDailyStatus(WorklogModel worklog) {
		worklogRepository.save(WorkLogMapper.mapModelToEntity(worklog));
	}
	
	@Override
	@Transactional
	public WorklogModel fetchById(String workLogId) {
		Optional<WorklogEntity> findById = worklogRepository.findById(workLogId);
		if(findById.isEmpty()) {
			throw new WorkLogNotFoundException("Worklog not found!! Please provide proper Id.");
		}
		return WorkLogMapper.mapEntityToModel(findById.get());
	}
	
	@Override
	@Transactional
	public List<WorklogModel> fetchByName(String userName) {
		List<WorklogEntity> findByUserName = worklogRepository.findByUserName(userName);
		if(findByUserName.isEmpty()) {
			throw new WorkLogNotFoundException("Worklog not found!! Please provide valid user name.");
		}
		return findByUserName.stream().map(WorkLogMapper::mapEntityToModel).toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<WorklogModel> fetchAllWorklog() {
		List<WorklogEntity> findByTitle = worklogRepository.findAll();
		return findByTitle.stream().map(WorkLogMapper::mapEntityToModel).toList();
	}

	@Override
	public List<WorklogModel> getWorklogByUserNameFilterInput(String userName, String filterInput) {
		List<WorklogEntity> worklogByKeyword = new ArrayList<>();
		if ("leave".equalsIgnoreCase(filterInput) || "training".equalsIgnoreCase(filterInput)) {
			worklogByKeyword = worklogRepository.findByKeywordAndWorkDetails(userName, filterInput);
		} else if ("done".equalsIgnoreCase(filterInput) || "in progress".equalsIgnoreCase(filterInput)) {
			worklogByKeyword = worklogRepository.findByKeywordAndStatus(userName, filterInput);
		}
		return worklogByKeyword.stream().map(WorkLogMapper::mapEntityToModel).toList();
	}

	@Override
	public List<String> findUsersWithEntriesForDate(String projectName, LocalDate date) {
		return worklogRepository.findUsersWithEntriesForDate(projectName, date);
	}
}
