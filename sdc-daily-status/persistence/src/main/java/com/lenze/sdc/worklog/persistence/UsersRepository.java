package com.lenze.sdc.worklog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<UserEntity, String> {
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.userName = :userName")
	public UserEntity findByName(@Param("userName") String userName);
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.userId = :userId")
	public UserEntity findByUserId(@Param("userId") String userId);
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.project = :projectName")
	public List<UserEntity> findAllUsersByProjectName(@Param("projectName") String projectName);
	
    @Modifying
    @Query(value = "delete from #{#entityName} u where u.userName = :userName and u.userId = :userId", nativeQuery = true)
	public void deleteByIdAndName(@Param("userId") String userId, @Param("userName") String userName);
}