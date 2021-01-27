package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;

public interface CommentService extends IBaseService<Comment> {

    boolean findUserAndAnnouncement(Comment comment);

}
