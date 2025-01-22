package com.lenze.sdc.worklog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<UserEntity, String> {
	
	@Query("SELECT u FROM #{#entityName} u WHERE lower(u.userName) = :userName")
	public UserEntity findByName(@Param("userName") String userName);
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.project = :projectName")
	public List<UserEntity> findAllUsersByProjectName(@Param("projectName") String projectName);
}