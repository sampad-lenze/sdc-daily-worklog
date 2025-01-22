package com.lenze.sdc.worklog.core.exception;

public class UserNotfoundException extends RuntimeException{

	private static final long serialVersionUID = -357206352039308609L;

	public UserNotfoundException(String message) {
		super(message);
	}
}
