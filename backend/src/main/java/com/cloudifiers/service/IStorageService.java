package com.cloudifiers.service;

import java.io.File;
import java.net.URL;

import com.amazonaws.services.s3.model.S3Object;

public interface IStorageService {

	public void uploadFile(String key, File file);

	public void deleteFile(String key);

	public S3Object getFile(String key);

	public URL getFileUrl(String key);
}
