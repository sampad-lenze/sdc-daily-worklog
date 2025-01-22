package com.lenze.sdc.worklog.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "users")
@IdClass(UsersEmbeddedId.class)
public class UserEntity {
	@Id
	@Column(name = "user_id", updatable = false, nullable = false)
	private String userId;
	@Id
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "gender", nullable = false)
    private String userGender;
    @Column(name = "designation", nullable = false)
    private String userDesignation;
    @Column(name = "project", nullable = false)
    private String project;
	public UserEntity(String userId, String userName, String userGender, String userDesignation,
			String project) {
		this.userId = userId;
		this.userName = userName;
		this.userGender = userGender;
		this.userDesignation = userDesignation;
		this.project = project;
	}
}
