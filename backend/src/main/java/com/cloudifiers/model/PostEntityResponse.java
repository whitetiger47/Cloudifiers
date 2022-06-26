package com.cloudifiers.model;

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
public class PostEntityResponse extends ResponseStatus {

	@JsonProperty("post")
	private PostEntity postEntity;

	public PostEntityResponse(Boolean success, String errorCode, PostEntity postEntity) {
		super(success, errorCode);
		this.postEntity = postEntity;
	}
}
