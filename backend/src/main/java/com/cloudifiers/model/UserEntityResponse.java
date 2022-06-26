package com.cloudifiers.model;

import com.cloudifiers.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityResponse extends ResponseStatus {

	@JsonProperty("user")
	private UserEntity userEntity;

	public UserEntityResponse(Boolean success, String errorCode, UserEntity userEntity) {
		super(success, errorCode);
		this.userEntity = userEntity;
	}
}
