package com.lenze.sdc.worklog.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WorklogRepository extends JpaRepository<WorklogEntity, String> {
	
	public List<WorklogEntity> findByUserName(String userName);
	
    @Modifying
    @Query(value = "delete from #{#entityName} l where l.userName = :userName", nativeQuery = true)
	public void deleteByUserName(@Param("userName") String userName);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.workDetails like %:keyword% and YEAR(l.date) = YEAR(CURRENT_DATE)")
    public List<WorklogEntity> findByKeywordAndWorkDetails(@Param("userName") String userName, @Param("keyword") String keyword);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.status like %:keyword% and YEAR(l.date) = YEAR(CURRENT_DATE)")
    public List<WorklogEntity> findByKeywordAndStatus(@Param("userName") String userName, @Param("keyword") String keyword);
	
	@Query("SELECT l.userName FROM #{#entityName} l WHERE l.projectName = :projectName AND l.date = :date")
	public List<String> findUsersWithEntriesForDate(@Param("projectName") String projectName, @Param("date") LocalDate date);
}