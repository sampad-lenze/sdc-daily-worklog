package com.lenze.sdc.worklog.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@SuperBuilder
public class UserModel {
	@NonNull
	private String userId;
	@NonNull
	private String userName;
	@NonNull
    private String designation;
    @NonNull
    private String gender;
    @NonNull
    private String project;
}
