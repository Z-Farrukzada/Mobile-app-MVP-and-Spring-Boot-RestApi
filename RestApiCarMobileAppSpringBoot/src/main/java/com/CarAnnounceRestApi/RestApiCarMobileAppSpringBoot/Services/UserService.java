package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomAuthException;

public interface UserService {

    User register(String username, String email, String password, String phone, int cityId) throws CustomAuthException;
    User validate(String email,String password) throws  CustomAuthException;

}
