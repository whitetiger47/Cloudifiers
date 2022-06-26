package com.cloudifiers.model;

public enum Error {

	NO_ERROR("E001"), USER_NOT_FOUND("E002"), INTERNAL_SERVER_ERROR("E500");

	private String errorCode;

	private Error(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
