package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import java.util.Map;

public interface UserService {

    Map<String,String> register(Map<String, String> userMap);
    Map<String,String> validate(Map<String,String> userMap);
    Map<String,String> findEmailChangePassword(Map<String,String> userMap) ;

}
