package com.cloudifiers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Configuration {

	@Value("${cloud.aws.credentials.secret-access-key}")
	private String secretAccessKey;

	@Value("${cloud.aws.credentials.access-key-id}")
	private String accessKeyId;

	@Value("${cloud.aws.credentials.session-token}")
	private String sessionToken;
	
	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonS3 configureAmazonS3Client() {
		AWSCredentials awsCredentials = new BasicSessionCredentials(accessKeyId, secretAccessKey, sessionToken);
		AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(awsStaticCredentialsProvider)
				.withRegion(region)
				.build();
	}
}
