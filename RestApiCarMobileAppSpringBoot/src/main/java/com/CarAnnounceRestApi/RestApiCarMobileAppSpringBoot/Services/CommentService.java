package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CommentDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;

import java.util.Map;

public interface CommentService extends IBaseService<CommentDTO> {

    Map<String,String> findUserAndAnnouncement(CommentDTO comment);

}
