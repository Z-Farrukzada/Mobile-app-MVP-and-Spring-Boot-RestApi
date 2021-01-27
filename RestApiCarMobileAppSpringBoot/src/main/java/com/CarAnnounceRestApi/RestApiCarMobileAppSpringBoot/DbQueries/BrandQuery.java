package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandQuery {

    public  String SQL_ALL_BRAND = "SELECT * FROM brand";
    public  String SQL_FIND_BY_ID_BRAND = "SELECT * FROM brand WHERE id = ?";
    public  String SQL_CREATE_NEW_BRAND = "INSERT INTO brand(name) VALUES(?)";
    public  String SQL_UPDATED_BRAND ="UPDATE brand SET name = ? WHERE id = ? ";
    public  String SQL_DELETED_BRAND ="DELETE FROM brand WHERE id = ? ";

}
