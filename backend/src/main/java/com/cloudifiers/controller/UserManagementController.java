package com.cloudifiers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudifiers.constants.CloudifiersConstants;
import com.cloudifiers.constants.CloudifiersConstants.ApiEndPoints;
import com.cloudifiers.constants.CloudifiersConstants.ControllerInfo;
import com.cloudifiers.entity.UserEntity;
import com.cloudifiers.model.Error;
import com.cloudifiers.model.LoginRequestModel;
import com.cloudifiers.model.ResponseStatus;
import com.cloudifiers.model.UserEntityListResponse;
import com.cloudifiers.model.UserEntityResponse;
import com.cloudifiers.service.IUserManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = ControllerInfo.USER_MANAGEMENT_CONTROLLER_VALUE, description = ControllerInfo.USER_MANAGEMENT_CONTROLLER_DESC)
@RestController
@RequestMapping(CloudifiersConstants.BASE_API_URL)
@CrossOrigin
public class UserManagementController {

	@Autowired
	private IUserManagementService userManagementService;

	@ApiOperation(value = ApiEndPoints.FETCH_USER_DESC, response = UserEntityResponse.class)
	@GetMapping(ApiEndPoints.FETCH_USER_URL)
	public ResponseEntity<ResponseStatus> fetchUser(@PathVariable("userId") Integer userId) throws Exception {
		return new ResponseEntity<ResponseStatus>(new UserEntityResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				userManagementService.fetchUser(userId)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.SAVE_USER_DESC, response = UserEntityResponse.class)
	@PostMapping(ApiEndPoints.SAVE_USER_URL)
	public ResponseEntity<ResponseStatus> saveUser(@RequestBody UserEntity userEntity) throws Exception {
		return new ResponseEntity<ResponseStatus>(new UserEntityResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				userManagementService.saveUser(userEntity)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.LOGIN_DESC, response = UserEntityResponse.class)
	@PostMapping(ApiEndPoints.LOGIN_URL)
	public ResponseEntity<ResponseStatus> fetchUser(@RequestBody LoginRequestModel loginRequestModel) throws Exception {
		return new ResponseEntity<ResponseStatus>(new UserEntityResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				userManagementService.fetchUser(loginRequestModel)), HttpStatus.OK);
	}

	@ApiOperation(value = ApiEndPoints.SEARCH_USER_DESC, response = UserEntityListResponse.class)
	@GetMapping(ApiEndPoints.SEARCH_USER_URL)
	public ResponseEntity<ResponseStatus> searchUser(@PathVariable("keyword") String keyword) throws Exception {
		return new ResponseEntity<ResponseStatus>(new UserEntityListResponse(Boolean.TRUE,
				Error.NO_ERROR.getErrorCode(), userManagementService.searchUsers(keyword)), HttpStatus.OK);
	}
}
