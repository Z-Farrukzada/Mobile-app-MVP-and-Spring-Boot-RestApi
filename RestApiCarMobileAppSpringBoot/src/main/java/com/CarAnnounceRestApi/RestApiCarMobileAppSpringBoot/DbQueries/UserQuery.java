package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserQuery {

    public  String SQL_CREATE_USER = "INSERT INTO User(username,email,password,phone,city_id) VALUES(?, ?, ?, ?, ?)";
    public  String SQL_USER_FIND_BY_ID = "SELECT username FROM User  WHERE id= ?";
    public  String SQL_USER_FIND_EMAIL_COUNT = "SELECT COUNT(*) FROM User WHERE email = ?";
    public  String SQL_USER_FIND_EMAIL = "SELECT id,username,email,password,phone,city_id FROM User WHERE email= ?";
    public  String SQL_FIND_EMAIL_AND_CHANGE_PASSWORD = "UPDATE user SET password = ? WHERE email = ?";

}
