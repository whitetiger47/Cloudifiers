package com.cloudifiers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cloudifiers.constants.CloudifiersConstants;
import com.cloudifiers.constants.CloudifiersConstants.ApiEndPoints;
import com.cloudifiers.model.Error;
import com.cloudifiers.model.PostModelListResponse;
import com.cloudifiers.model.ResponseStatus;
import com.cloudifiers.service.ITimelineManagementService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(CloudifiersConstants.BASE_API_URL)
public class TimelineManagementController {

	@Autowired
	private ITimelineManagementService timelineManagementService;

	@ApiOperation(value = ApiEndPoints.GENERATE_TIMELINE_DESC, response = PostModelListResponse.class)
	@GetMapping(ApiEndPoints.GENERATE_TIMELINE_URL)
	public ResponseEntity<ResponseStatus> getTimeline(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<ResponseStatus>(new PostModelListResponse(Boolean.TRUE, Error.NO_ERROR.getErrorCode(),
				timelineManagementService.generateTimeline(userId)), HttpStatus.OK);
	}
}
