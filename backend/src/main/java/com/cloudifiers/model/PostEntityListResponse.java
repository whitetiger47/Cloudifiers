package com.cloudifiers.model;

import java.util.List;

import com.cloudifiers.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostEntityListResponse extends ResponseStatus {

	@JsonProperty("posts")
	private List<PostEntity> posts;

	public PostEntityListResponse(Boolean success, String errorCode, List<PostEntity> posts) {
		super(success, errorCode);
		this.posts = posts;
	}
}
