package com.cloudifiers.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudifiers.entity.CommentEntity;
import com.cloudifiers.entity.LikeEntity;
import com.cloudifiers.entity.LikeEntity.LikeId;
import com.cloudifiers.entity.PostEntity;
import com.cloudifiers.entity.UserEntity;
import com.cloudifiers.model.CommentModel;
import com.cloudifiers.model.PostModel;
import com.cloudifiers.repository.CommentRepository;
import com.cloudifiers.repository.LikeRepository;
import com.cloudifiers.repository.PostRepository;
import com.cloudifiers.repository.UserRepository;

@Service
public class PostManagementService implements IPostManagementService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addPost(PostEntity postEntity) {
		postRepository.save(postEntity);
	}

	@Override
	public PostEntity fetchPost(Integer postId) {
		return postRepository.findById(postId).get();
	}

	@Override
	public List<PostModel> fetchPostByUserId(Integer userId) {
		return postRepository.getPostsByUserId(userId).stream().map(postEntity -> {
			return new PostModel(postEntity, userRepository.findById(postEntity.getUserId()).get());
		}).collect(Collectors.toList());
	}

	@Override
	public void deletePost(Integer postId) {
		postRepository.deleteById(postId);
	}

	@Override
	@Transactional
	public void likePost(Integer postId, Integer userId) {
		// Increase likes value by one
		PostEntity postEntity = postRepository.findById(postId).get();
		postEntity.setLikes(postEntity.getLikes() + 1);
		postRepository.save(postEntity);

		// Add like
		LikeEntity likeEntity = new LikeEntity(new LikeId(postId, userId));
		likeRepository.save(likeEntity);
	}

	@Override
	@Transactional
	public void dislikePost(Integer postId, Integer userId) {
		// Decrease like value by one
		PostEntity postEntity = postRepository.findById(postId).get();
		postEntity.setLikes(postEntity.getLikes() - 1);
		postRepository.save(postEntity);

		// remove like
		likeRepository.deleteById(new LikeId(postId, userId));
	}

	@Override
	public Boolean checkLikeStatus(Integer postId, Integer userId) {
		return likeRepository.existsById(new LikeId(postId, userId));
	}

	@Override
	public Integer getTotalLikes(Integer postId) {
		return postRepository.findById(postId).get().getLikes();
	}

	@Override
	public List<UserEntity> getUsersLikedPost(Integer postId) {
		return likeRepository.getUsersLikedPost(postId);
	}

	@Override
	public void addComment(CommentEntity commentEntity) {
		commentRepository.save(commentEntity);
	}

	@Override
	public void deleteComment(Integer commentId) {
		commentRepository.deleteById(commentId);
	}

	@Override
	public List<CommentModel> fetchComments(Integer postId) {
		return commentRepository.findByPostId(postId).stream().map(comment -> {
			UserEntity userEntity = userRepository.findById(comment.getUserId()).get();
			return new CommentModel(comment, userEntity);
		}).collect(Collectors.toList());
	}
}
