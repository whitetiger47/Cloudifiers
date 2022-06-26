package com.cloudifiers.model;

import java.util.List;

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
public class UserEntityListResponse extends ResponseStatus {

	@JsonProperty("userList")
	private List<UserEntity> userEntityList;

	public UserEntityListResponse(Boolean success, String errorCode, List<UserEntity> userEntityList) {
		super(success, errorCode);
		this.userEntityList = userEntityList;
	}

}
