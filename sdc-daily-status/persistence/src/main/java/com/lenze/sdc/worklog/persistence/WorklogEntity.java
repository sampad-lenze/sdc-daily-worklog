package com.lenze.sdc.worklog.persistence;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "worklog")
public class WorklogEntity {
	@Id
	@Column(name = "worklog_id", updatable = false, nullable = false)
	private String worklogId;
    @Column(name = "week_number")
    private long weekNumber;
    @Column(name = "work_details")
    private String workDetails;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "project", nullable = false)
    private String projectName;
    @Column(name = "work_hours")
    private double workingHours;
    @Column(name = "status")
    private String status;
    
	public WorklogEntity(String worklogId, long weekNumber, String workDetails, LocalDate date, String userName,
			String projectName, double workingHours, String status) {
		this.worklogId = worklogId;
		this.weekNumber = weekNumber;
		this.workDetails = workDetails;
		this.date = date;
		this.userName = userName;
		this.projectName = projectName;
		this.workingHours = workingHours;
		this.status = status;
	}
    
    
}
