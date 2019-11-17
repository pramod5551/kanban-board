package com.kanbanboard.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProblemDetailsExceptionHandler {

	@ExceptionHandler(ProblemDetailsException.class)
	public ResponseEntity<ProblemDetailsException> handleProblemDetailsException(ProblemDetailsException exception) {

		return ResponseEntity.status(exception.getStatus()).body(exception);

	}

}
