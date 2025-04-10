package com.msproject.complaintresponse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComplaintNotFoundException  extends RuntimeException {
    public ComplaintNotFoundException(String message) {
        super(message);
    }
}