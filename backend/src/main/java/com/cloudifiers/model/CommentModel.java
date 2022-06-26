package com.cloudifiers.model;

import com.cloudifiers.entity.CommentEntity;
import com.cloudifiers.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

	private CommentEntity commentEntity;

	private UserEntity userEntity;

}
