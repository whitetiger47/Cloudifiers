package com.cloudifiers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudifiers.constants.CloudifiersConstants.DatabaseConstants;
import com.cloudifiers.constants.CloudifiersConstants.ParameterConstants;
import com.cloudifiers.entity.LikeEntity;
import com.cloudifiers.entity.LikeEntity.LikeId;
import com.cloudifiers.entity.UserEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikeId> {
	
	@Query(value = DatabaseConstants.FETCH_USERS_LIKED_POST_QUERY)
	public List<UserEntity> getUsersLikedPost(@Param(ParameterConstants.POST_ID) Integer postId);
}
