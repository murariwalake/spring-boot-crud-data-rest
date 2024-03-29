package com.murariwalake.springboot.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentGlobalExceptionHandler {

	@ExceptionHandler(StudentClientException.class)
	public ResponseEntity<StudentErrorResponse> handleException(StudentClientException e) {
		StudentErrorResponse error = StudentErrorResponse.builder()
				.status(e.getHttpStatus().value())
				.error(e.getMessage())
				.timestamp(System.currentTimeMillis())
				.build();
		return ResponseEntity.status(e.getHttpStatus()).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StudentErrorResponse> handleException(Exception e) {
		StudentErrorResponse error = StudentErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(e.getMessage())
				.timestamp(System.currentTimeMillis())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
