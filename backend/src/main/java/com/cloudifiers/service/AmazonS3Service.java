package com.cloudifiers.service;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class AmazonS3Service implements IStorageService {

	@Value("${cloud.aws.bucket.bucket-name}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonS3;

	@Override
	public void uploadFile(String key, File file) {
		amazonS3.putObject(bucketName, key, file);
	}

	@Override
	public URL getFileUrl(String key) {
		return amazonS3.getUrl(bucketName, key);
	}

	@Override
	public void deleteFile(String key) {
		amazonS3.deleteObject(bucketName, key);
	}

	@Override
	public S3Object getFile(String key) {
		return amazonS3.getObject(bucketName, key);
	}
	
	public void assignReadPermission(String key) {
		
	}
}
