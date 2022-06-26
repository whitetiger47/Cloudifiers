package com.cloudifiers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.model.CreateTopicResult;
import com.cloudifiers.entity.UserEntity;
import com.cloudifiers.exception.NoUserFoundException;
import com.cloudifiers.model.LoginRequestModel;
import com.cloudifiers.repository.UserRepository;

@Service
public class UserManagementService implements IUserManagementService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IAmazonSNSService snsService;

	@Override
	public UserEntity fetchUser(Integer userId) throws Exception {
		return userRepository.findById(userId).orElseThrow(() -> new NoUserFoundException());
	}

	@Override
	public UserEntity fetchUser(LoginRequestModel loginRequestModel) throws Exception {
		UserEntity userEntity = userRepository.fetchUser(loginRequestModel.getEmail(), loginRequestModel.getPassword())
				.orElseThrow(() -> new NoUserFoundException());
		if(userEntity.getTopicArn() != null) {
			snsService.notify(userEntity.getTopicArn());
		}
		return userEntity;
	}

	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		if (userEntity.getUserId() == null) {
			userEntity = userRepository.save(userEntity);
			CreateTopicResult result = snsService.createTopic(userEntity.getUserId().toString());
			userEntity.setTopicArn(result.getTopicArn());
			userRepository.save(userEntity);
			snsService.subscribe(userEntity.getTopicArn(), userEntity.getEmail());
		} else {
			userEntity = userRepository.save(userEntity);
		}
		return userEntity;
	}

	@Override
	public List<UserEntity> searchUsers(String keyword) {
		return userRepository.searchUser(keyword.toLowerCase());
	}
}
