package com.murariwalake.springboot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentGlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentClientException e) {
		StudentErrorResponse error = StudentErrorResponse.builder()
				.status(e.getHttpStatus().value())
				.error(e.getMessage())
				.timestamp(System.currentTimeMillis())
				.build();
		return ResponseEntity.status(e.getHttpStatus()).body(error);
	}
}
