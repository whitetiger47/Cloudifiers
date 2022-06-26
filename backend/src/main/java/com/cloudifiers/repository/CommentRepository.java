package com.cloudifiers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloudifiers.constants.CloudifiersConstants.DatabaseConstants;
import com.cloudifiers.constants.CloudifiersConstants.ParameterConstants;
import com.cloudifiers.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

	@Query(value = DatabaseConstants.FIND_COMMENT_BY_POST_ID_QUERY)
	public List<CommentEntity> findByPostId(@Param(ParameterConstants.POST_ID) Integer postId);

}
