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
public class FriendListResponse extends ResponseStatus {

	@JsonProperty("friendList")
	private List<UserEntity> userList;

	public FriendListResponse(Boolean success, String errorCode, List<UserEntity> userList) {
		super(success, errorCode);
		this.userList = userList;
	}
}
