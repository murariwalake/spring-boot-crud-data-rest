package com.murariwalake.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentErrorResponse {
	private Long timestamp;
	private Integer status;
	private String error;
}
