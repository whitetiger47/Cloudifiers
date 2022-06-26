package com.cloudifiers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudifiers.constants.CloudifiersConstants.DatabaseConstants;
import com.cloudifiers.constants.CloudifiersConstants.ParameterConstants;
import com.cloudifiers.entity.FriendEntity;
import com.cloudifiers.entity.UserEntity;

@Repository
public interface FriendListManagementRepository extends JpaRepository<FriendEntity, FriendEntity.FriendId> {

	@Query(value = DatabaseConstants.FETCH_FRIENDS_QUERY)
	List<UserEntity> findFriendsOf(@Param(ParameterConstants.USER_ID) Integer userId);

}
