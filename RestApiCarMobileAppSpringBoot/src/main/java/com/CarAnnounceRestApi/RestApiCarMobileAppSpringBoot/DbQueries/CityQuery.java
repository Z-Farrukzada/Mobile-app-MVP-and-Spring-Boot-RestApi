package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CityQuery {

    public String SQL_ALL_CITY_LIST = "SELECT * FROM city";
    public String SQL_FIND_BY_ID_CITY = "SELECT * FROM city WHERE id = ?";
    public String SQL_CREATED_CITY = "INSERT INTO city(name) VALUES(?)";
    public String SQL_UPDATED_CITY="UPDATE city SET name = ? WHERE id = ?";
    public String SQL_DELETED_CITY = "DELETE FROM city WHERE id = ?";
    public String SQL_WITH_NAME_FIND_ID = "SELECT id FROM city WHERE name = ? ";

}
