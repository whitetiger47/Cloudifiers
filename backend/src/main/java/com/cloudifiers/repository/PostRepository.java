package com.cloudifiers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudifiers.constants.CloudifiersConstants.DatabaseConstants;
import com.cloudifiers.constants.CloudifiersConstants.ParameterConstants;
import com.cloudifiers.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	@Query(value = DatabaseConstants.GET_POSTS_BY_USER_ID_QUERY)
	public List<PostEntity> getPostsByUserId(@Param(ParameterConstants.USER_ID) Integer userId);
	
	@Query(value = DatabaseConstants.GET_TIMELINE_QUERY)
	public List<PostEntity> getTimeline(@Param(ParameterConstants.USER_ID) Integer userId);

}
