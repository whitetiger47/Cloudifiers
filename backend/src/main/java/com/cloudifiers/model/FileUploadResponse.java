package com.cloudifiers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileUploadResponse extends ResponseStatus {

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("url")
	private String url;

	public FileUploadResponse(Boolean success, String errorCode, String fileName, String url) {
		super(success, errorCode);
		this.fileName = fileName;
		this.url = url;
	}
}
