package com.cloudifiers.service;

import java.util.List;

import com.cloudifiers.model.PostModel;

public interface ITimelineManagementService {

	public List<PostModel> generateTimeline(Integer userId);

}
