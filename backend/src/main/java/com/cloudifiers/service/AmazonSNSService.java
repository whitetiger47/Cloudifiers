package com.cloudifiers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

@Service
public class AmazonSNSService implements IAmazonSNSService {

	@Autowired
	private AmazonSNSClient snsClient;

	private static final String PROTOCOL = "email";

	@Override
	public SubscribeResult subscribe(String topicArn, String email) {
		SubscribeRequest request = new SubscribeRequest(topicArn, PROTOCOL, email);
		return snsClient.subscribe(request);
	}

	@Override
	public CreateTopicResult createTopic(String topicName) {
		return snsClient.createTopic(topicName);
	}

	@Override
	public PublishResult notify(String topicArn) {
		return snsClient.publish(topicArn, "You just logged in. If it is not you, please change your credentials.",
				"Login Notification");
	}

}
