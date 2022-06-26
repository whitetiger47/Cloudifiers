package com.cloudifiers.exception;

public class NoUserFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_MSG = "User not found.";

	public NoUserFoundException() {
		super(ERROR_MSG);
	}
}
