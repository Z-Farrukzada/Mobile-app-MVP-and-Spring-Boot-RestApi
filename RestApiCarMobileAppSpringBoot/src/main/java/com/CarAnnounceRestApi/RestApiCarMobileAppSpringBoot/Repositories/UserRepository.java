package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomAuthException;

public interface UserRepository {

    Integer create(String Username,String email,String password,String phone,int cityId) throws CustomAuthException;

    User findEmailAndPassword(String email, String password) throws  CustomAuthException;

    Long getCountByEmail(String email);

    User findById(Integer Id);

}
