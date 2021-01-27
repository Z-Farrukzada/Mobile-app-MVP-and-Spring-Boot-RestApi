package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CarBanQuery {

    public  String  SQL_ALL_LIST_CAR_BANS= "SELECT * FROM ban";
    public  String SQL_FIND_BY_ID_BAN = "SELECT * FROM ban WHERE id = ?";
    public  String SQL_CREATED_BAN = "INSERT INTO ban(name) VALUES(?)";
    public  String SQL_UPDATED_BAN="UPDATE ban SET name = ? WHERE id = ?";
    public  String SQL_DELETED_BAN = "DELETE FROM ban WHERE id = ?";

}
