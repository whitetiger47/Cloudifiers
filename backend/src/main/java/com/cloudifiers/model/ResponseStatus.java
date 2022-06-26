package com.cloudifiers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {

	@JsonProperty("success")
	private Boolean success;
	
	@JsonProperty("errorCode")
	private String errorCode;

}
