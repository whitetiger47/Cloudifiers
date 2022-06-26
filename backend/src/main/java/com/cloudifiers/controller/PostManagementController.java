package com.cloudifiers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudifiers.constants.CloudifiersConstants;
import com.cloudifiers.constants.CloudifiersConstants.ApiEndPoints;
import com.cloudifiers.entity.CommentEntity;
import com.cloudifiers.entity.PostEntity;
import com.cloudifiers.model.CommentListResponse;
import com.cloudifiers.model.Error;
import com.cloudifiers.model.PostEntityResponse;
import com.cloudifiers.model.PostModelListResponse;
import com.cloudifiers.model.ResponseStatus;
import com.cloudifiers.model.StatusResponse;
import com.cloudifiers.model.TotalLikeResponse;
import com.cloudifiers.model.UserEntityListResponse;
import com.cloudifiers.service.IPostManagementService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(CloudifiersConstants.BASE_API_URL)
@CrossOrigin
public class PostManagementController {

	@Autowired
	private IPostManagementService postManagementService;

	@ApiOperation(value = ApiEndPoints.ADD_POST_DESC, response = ResponseStatus.class)
	@PostMapping(ApiEndPoints.ADD_POST_URL)
	public ResponseEntity<ResponseStatus> addPost(@RequestBody PostEntity postEntity) throws Exception {
		postManagementService.addPost(postEntity);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.FETCH_POST_DESC, response = PostEntityResponse.class)
	@GetMapping(ApiEndPoints.FETCH_POST_URL)
	public ResponseEntity<ResponseStatus> fetchPost(@PathVariable("postId") Integer postId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new PostEntityResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				postManagementService.fetchPost(postId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.FETCH_POSTS_DESC, response = PostModelListResponse.class)
	@GetMapping(ApiEndPoints.FETCH_POSTS_URL)
	public ResponseEntity<ResponseStatus> getPostsByUserId(@PathVariable("userId") Integer userId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new PostModelListResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				postManagementService.fetchPostByUserId(userId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.DELETE_POST_DESC, response = ResponseStatus.class)
	@DeleteMapping(ApiEndPoints.DELETE_POST_URL)
	public ResponseEntity<ResponseStatus> deletePost(@PathVariable("postId") Integer postId) throws Exception {
		postManagementService.deletePost(postId);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.LIKE_POST_DESC, response = ResponseStatus.class)
	@PostMapping(ApiEndPoints.LIKE_POST_URL)
	public ResponseEntity<ResponseStatus> likePost(@PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId) throws Exception {
		postManagementService.likePost(postId, userId);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.DISLIKE_POST_DESC, response = ResponseStatus.class)
	@DeleteMapping(ApiEndPoints.DISLIKE_POST_URL)
	public ResponseEntity<ResponseStatus> dislikePost(@PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId) throws Exception {
		postManagementService.dislikePost(postId, userId);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.CHECK_LIKE_STATUS_DESC, response = StatusResponse.class)
	@GetMapping(ApiEndPoints.CHECK_LIKE_STATUS_URL)
	public ResponseEntity<ResponseStatus> checkLikeStatus(@PathVariable("postId") Integer postId,
			@PathVariable("userId") Integer userId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new StatusResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				postManagementService.checkLikeStatus(postId, userId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.GET_TOTAL_LIKES_DESC, response = TotalLikeResponse.class)
	@GetMapping(ApiEndPoints.GET_TOTAL_LIKES_URL)
	public ResponseEntity<ResponseStatus> getTotalLikes(@PathVariable("postId") Integer postId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new TotalLikeResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				postManagementService.getTotalLikes(postId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.GET_USERS_LIKED_POST_DESC, response = UserEntityListResponse.class)
	@GetMapping(ApiEndPoints.GET_USERS_LIKED_POST_URL)
	public ResponseEntity<ResponseStatus> getUserLikedPost(@PathVariable("postId") Integer postId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new UserEntityListResponse(Boolean.TRUE,
				Error.NO_ERROR.getErrorCode(), postManagementService.getUsersLikedPost(postId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.ADD_COMMENT_DESC, response = ResponseStatus.class)
	@PostMapping(ApiEndPoints.ADD_COMMENT_URL)
	public ResponseEntity<ResponseStatus> addComment(@RequestBody CommentEntity commentEntity) throws Exception {
		postManagementService.addComment(commentEntity);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.DELETE_COMMENT_DESC, response = ResponseStatus.class)
	@DeleteMapping(ApiEndPoints.DELETE_COMMENT_URL)
	public ResponseEntity<ResponseStatus> deleteComment(@PathVariable("commentId") Integer commentId) throws Exception {
		postManagementService.deleteComment(commentId);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()),
				HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.FETCH_COMMENTS_DESC, response = CommentListResponse.class)
	@GetMapping(ApiEndPoints.FETCH_COMMNETS_URL)
	public ResponseEntity<ResponseStatus> fetchComments(@PathVariable("postId") Integer postId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new CommentListResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				postManagementService.fetchComments(postId)), HttpStatus.OK);
	}
}
