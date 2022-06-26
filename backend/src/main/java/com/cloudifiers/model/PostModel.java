package com.cloudifiers.model;

import com.cloudifiers.entity.PostEntity;
import com.cloudifiers.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {

	private PostEntity postEntity;

	private UserEntity userEntity;

}
