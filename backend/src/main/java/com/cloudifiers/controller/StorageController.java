package com.cloudifiers.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudifiers.constants.CloudifiersConstants;
import com.cloudifiers.constants.CloudifiersConstants.ApiEndPoints;
import com.cloudifiers.constants.CloudifiersConstants.ControllerInfo;
import com.cloudifiers.model.Error;
import com.cloudifiers.model.FileUploadResponse;
import com.cloudifiers.model.ResponseStatus;
import com.cloudifiers.service.IStorageService;
import com.cloudifiers.util.Converter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = ControllerInfo.STORAGE_CONTROLLER_VALUE, description = ControllerInfo.STORAGE_CONTROLLER_DESC)
@RestController
@RequestMapping(CloudifiersConstants.BASE_API_URL)
@CrossOrigin
public class StorageController {

	@Autowired
	private IStorageService storageService;

	@ApiOperation(value = ApiEndPoints.UPLOAD_FILE_DESC, response = FileUploadResponse.class)
	@PostMapping(ApiEndPoints.UPLOAD_FILE_URL)
	public ResponseEntity<ResponseStatus> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) {
		File file = Converter.converMultipartFileToFile(multipartFile);
		String fileName = Converter.generateUniqueName(file.getName());
		storageService.uploadFile(fileName, file);
		file.delete();

		String url = storageService.getFileUrl(fileName).toString();
		FileUploadResponse fileUploadResponse = new FileUploadResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				fileName, url);
		return new ResponseEntity<ResponseStatus>(fileUploadResponse, HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.REMOVE_FILE_DESC, response = ResponseStatus.class)
	@DeleteMapping(ApiEndPoints.REMOVE_FILE_URL)
	public ResponseEntity<ResponseStatus> removeFile(@PathVariable("fileName") String fileName) {
		storageService.deleteFile(fileName);
		return new ResponseEntity<ResponseStatus>(new ResponseStatus(Boolean.TRUE, Error.NO_ERROR.getErrorCode()), HttpStatus.OK);
	}
}
