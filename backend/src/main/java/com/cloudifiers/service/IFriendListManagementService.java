package com.cloudifiers.service;

import java.util.List;

import com.cloudifiers.entity.FriendEntity.FriendId;
import com.cloudifiers.entity.UserEntity;

public interface IFriendListManagementService {

	public Boolean addFriend(FriendId friendId);
	
	public Boolean removeFriend(FriendId friendId);
	
	public List<UserEntity> getFriends(Integer userId);
	
	public Boolean checkFriendStatus(Integer user1Id, Integer user2Id);
}
