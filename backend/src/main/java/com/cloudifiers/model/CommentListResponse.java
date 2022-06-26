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
public class CommentListResponse extends ResponseStatus {

	@JsonProperty("userList")
	private List<CommentModel> commentModelList;

	public CommentListResponse(Boolean success, String errorCode, List<CommentModel> commentModelList) {
		super(success, errorCode);
		this.commentModelList = commentModelList;
	}

}
