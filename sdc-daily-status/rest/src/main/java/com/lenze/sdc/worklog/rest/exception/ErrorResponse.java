package com.lenze.sdc.worklog.rest.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ErrorResponse {

    private int status;
    private List<String> message;
}
