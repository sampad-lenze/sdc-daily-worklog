package com.lenze.sdc.worklog.core.dao.service;

import java.util.List;

import com.lenze.sdc.worklog.core.model.UserModel;

public interface UsersDaoService {
	
	List<UserModel> findAllUsers();
	List<UserModel> findAllUsersByProject(String project);
	void registerUser(UserModel userModel);
	void deleteUser(String id);
	UserModel findById(String id);
	UserModel findByUserName(String name);
}
