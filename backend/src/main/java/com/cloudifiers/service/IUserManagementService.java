package com.cloudifiers.service;

import java.util.List;

import com.cloudifiers.entity.UserEntity;
import com.cloudifiers.model.LoginRequestModel;

public interface IUserManagementService {

	public UserEntity fetchUser(Integer userId) throws Exception;

	public UserEntity fetchUser(LoginRequestModel loginRequestModel) throws Exception;

	public UserEntity saveUser(UserEntity userEntity);
	
	public List<UserEntity> searchUsers(String keyword);
}
