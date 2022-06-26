package com.cloudifiers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AmazonSNSCOnfiguration {

	@Value("${cloud.aws.credentials.secret-access-key}")
	private String secretAccessKey;

	@Value("${cloud.aws.credentials.access-key-id}")
	private String accessKeyId;

	@Value("${cloud.aws.credentials.session-token}")
	private String sessionToken;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonSNSClient getAmazonSNSCLient() {
		AWSCredentials awsCredentials = new BasicSessionCredentials(accessKeyId, secretAccessKey, sessionToken);
		AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(region)
				.withCredentials(awsStaticCredentialsProvider).build();
	}

}
