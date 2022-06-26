package com.cloudifiers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudifiers.entity.FriendEntity;
import com.cloudifiers.entity.FriendEntity.FriendId;
import com.cloudifiers.entity.UserEntity;
import com.cloudifiers.repository.FriendListManagementRepository;

@Service
public class FriendListManagementService implements IFriendListManagementService {

	@Autowired
	private FriendListManagementRepository friendListManagementRepository;

	@Override
	@Transactional
	public Boolean addFriend(FriendId friendId) {
		try {
			FriendEntity friend = new FriendEntity(friendId);
			friendListManagementRepository.save(friend);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean removeFriend(FriendId friendId) {
		try {
			FriendEntity friend = new FriendEntity(friendId);
			friendListManagementRepository.delete(friend);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public List<UserEntity> getFriends(Integer userId) {
		try {
			return friendListManagementRepository.findFriendsOf(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean checkFriendStatus(Integer user1Id, Integer user2Id) {
		return friendListManagementRepository.existsById(new FriendId(user1Id, user2Id));
	}

}
