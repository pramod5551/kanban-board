package com.kanbanboard.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "stackTrace", "suppressed" })
public class ProblemDetailsException extends Exception {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String description;

	public ProblemDetailsException(HttpStatus status, String description) {
		this.status = status;
		this.description = description;

	}

}
