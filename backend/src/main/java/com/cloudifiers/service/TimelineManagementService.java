package com.cloudifiers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudifiers.model.PostModel;
import com.cloudifiers.repository.PostRepository;
import com.cloudifiers.repository.UserRepository;

@Service
public class TimelineManagementService implements ITimelineManagementService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<PostModel> generateTimeline(Integer userId) {
		return postRepository.getTimeline(userId).stream().map(postEntity -> {
			return new PostModel(postEntity, userRepository.findById(postEntity.getUserId()).get());
		}).collect(Collectors.toList());
	}

}
