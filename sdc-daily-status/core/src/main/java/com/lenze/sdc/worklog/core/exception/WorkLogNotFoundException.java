package com.lenze.sdc.worklog.core.exception;

public class WorkLogNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public WorkLogNotFoundException(String message) {
        super(message);
    }
}
