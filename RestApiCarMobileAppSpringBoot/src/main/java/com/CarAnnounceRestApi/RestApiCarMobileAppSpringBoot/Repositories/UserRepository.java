package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;

public interface UserRepository {

    Integer create(String Username,String email,String password,String phone,int cityId);

    User findEmailAndPassword(String email, String password);

    Long getCountByEmail(String email);

    User findById(Integer Id);

    int findEmailAndChangePassword(String email,String password);

}
