package com.cloudifiers.service;

import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeResult;

public interface IAmazonSNSService {

	public SubscribeResult subscribe(String topicId, String email);
	
	public CreateTopicResult createTopic(String topicId);
	
	public PublishResult notify(String topicId);
	
}
