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
    @Query(value = "delete from #{#entityName} where userName = :name", nativeQuery = true)
	public void deleteByUserName(@Param("name") String name);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.workDetails like %:keyword% and YEAR(l.date) = YEAR(CURRENT_DATE)")
    public List<WorklogEntity> findByKeywordAndWorkDetails(@Param("userName") String userName, @Param("keyword") String keyword);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.status like %:keyword% and YEAR(l.date) = YEAR(CURRENT_DATE)")
    public List<WorklogEntity> findByKeywordAndStatus(@Param("userName") String userName, @Param("keyword") String keyword);
	
	@Query("SELECT l FROM #{#entityName} l WHERE l.projectName = :projectName AND l.date = :date")
	public List<WorklogEntity> findUsersWithEntriesForDate(@Param("projectName") String projectName, @Param("date") LocalDate date);
	
	@Query("SELECT l FROM #{#entityName} l WHERE l.date = :date")
	public List<WorklogEntity> findAllUsersWithEntriesForDate(@Param("date") LocalDate date);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.date between :startDate and :endDate order by l.date ASC")
    public List<WorklogEntity> findByNameAndDateRange(@Param("userName") String userName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query("select l from #{#entityName} l where l.userName = :userName and l.date = :date")
    public WorklogEntity findByNameAndDate(@Param("userName") String userName, @Param("date") LocalDate date);
}