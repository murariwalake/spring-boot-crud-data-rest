package com.murariwalake.springboot.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class StudentClientException extends RuntimeException {
	private HttpStatus httpStatus;

	public StudentClientException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}


}

