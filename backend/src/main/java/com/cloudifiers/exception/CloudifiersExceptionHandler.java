package com.cloudifiers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cloudifiers.model.Error;
import com.cloudifiers.model.ResponseStatus;

@ControllerAdvice
public class CloudifiersExceptionHandler {

	@ExceptionHandler(value = { NoUserFoundException.class })
	public ResponseEntity<ResponseStatus> handleNoUserFoundError() {
		return new ResponseEntity<ResponseStatus>(
				new ResponseStatus(Boolean.FALSE, Error.USER_NOT_FOUND.getErrorCode()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ResponseStatus> handleInternalServerError() {
		return new ResponseEntity<ResponseStatus>(
				new ResponseStatus(Boolean.FALSE, Error.INTERNAL_SERVER_ERROR.getErrorCode()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
