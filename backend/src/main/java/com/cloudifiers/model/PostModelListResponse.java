package com.cloudifiers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostModelListResponse extends ResponseStatus {

	@JsonProperty("postModelList")
	private List<PostModel> postModelList;

	public PostModelListResponse(Boolean success, String errorCode, List<PostModel> postModelList) {
		super(success, errorCode);
		this.postModelList = postModelList;
	}

}
