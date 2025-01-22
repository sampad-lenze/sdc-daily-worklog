package com.lenze.sdc.worklog.daoservice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenze.sdc.worklog.core.dao.service.UsersDaoService;
import com.lenze.sdc.worklog.core.exception.UserNotfoundException;
import com.lenze.sdc.worklog.core.model.UserModel;
import com.lenze.sdc.worklog.mapper.UserMapper;
import com.lenze.sdc.worklog.persistence.UserEntity;
import com.lenze.sdc.worklog.persistence.UsersRepository;

@Service
@Transactional(readOnly = true)
public class UsersDaoServiceImpl implements UsersDaoService{

	private UsersRepository usersRepository;
	UsersDaoServiceImpl(UsersRepository usersRepository){
		this.usersRepository = usersRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserModel> findAllUsers() {
		List<UserEntity> users = usersRepository.findAll();
		return users.stream().map(UserMapper::mapEntityToModel).toList();
	}

	@Override
	@Transactional
	public void registerUser(UserModel userModel) {
		UserEntity userEntity = UserMapper.mapModelToEntity(userModel);
		usersRepository.save(userEntity);
	}

	@Override
	@Transactional
	public void deleteUser(String id) {
		usersRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserModel findById(String id) {
		Optional<UserEntity> optionalUser = usersRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new UserNotfoundException("User not found!! Please provide valid id.");
		}
		return UserMapper.mapEntityToModel(optionalUser.get());
	}

	@Override
	public UserModel findByUserName(String name) {
		UserEntity userEntity = usersRepository.findByName(name);
		if(Objects.isNull(userEntity)) {
			throw new UserNotfoundException("User not found!! Please provide valid name.");
		}
		return UserMapper.mapEntityToModel(userEntity);
	}

	@Override
	public List<UserModel> findAllUsersByProject(String project) {
		List<UserEntity> list = usersRepository.findAllUsersByProjectName(project);
		return list.stream().map(UserMapper::mapEntityToModel).toList();
	}
}
