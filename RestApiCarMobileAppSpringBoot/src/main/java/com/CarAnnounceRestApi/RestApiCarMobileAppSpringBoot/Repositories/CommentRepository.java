package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Comment;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository.IBaseRepository;

public interface CommentRepository extends IBaseRepository<Comment> {

      boolean isFindUserAndAnnounce(Comment comment);

}
