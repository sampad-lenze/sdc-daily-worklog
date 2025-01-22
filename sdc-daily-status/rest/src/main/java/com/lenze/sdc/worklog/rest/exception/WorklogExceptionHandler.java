package com.lenze.sdc.worklog.rest.exception;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lenze.sdc.worklog.core.exception.UserNotfoundException;
import com.lenze.sdc.worklog.core.exception.WorkLogNotFoundException;

@RestControllerAdvice
public class WorklogExceptionHandler {

	@ExceptionHandler(ExecutionException.class)
	public ResponseEntity<Void> handleExecutionException(ExecutionException executionException) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(WorkLogNotFoundException.class)
	ResponseEntity<ErrorResponse> handleWorklogNotFoundException(WorkLogNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage())),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotfoundException.class)
    ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotfoundException ex){
    	return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage())),
    			HttpStatus.NOT_FOUND);
    }
}