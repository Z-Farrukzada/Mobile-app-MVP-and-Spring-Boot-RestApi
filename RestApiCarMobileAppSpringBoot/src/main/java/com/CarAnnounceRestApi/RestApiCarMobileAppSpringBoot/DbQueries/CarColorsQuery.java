package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CarColorsQuery {

     public   String SQL_ALL_COLORS_LIST = "SELECT * FROM colors";
     public   String SQL_FIND_BY_ID_COLOR = "SELECT * FROM colors WHERE id = ?";
     public   String SQL_CREATED_COLOR = "INSERT INTO colors(name) VALUES(?)";
     public   String SQL_UPDATED_COLOR="UPDATE colors SET name = ? WHERE id = ?";
     public   String SQL_DELETED_COLOR = "DELETE FROM colors WHERE id = ?";

}
