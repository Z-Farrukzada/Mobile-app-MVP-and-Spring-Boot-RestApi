package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DetailQuery {

    public  String SQL_DETAILS_LIST= "SELECT * FROM details";
    public  String SQL_FIND_By_ID = "SELECT * FROM details WHERE id= ?";
    public  String SQL_CREATED_DETAIL = "INSERT INTO details(hatch,ABS,aircondition) VALUES(?,?,?)";
    public  String SQL_UPDATED_DETAIL="UPDATE details SET hatch = ? , ABS = ? , aircondition = ? WHERE id = ?";
    public  String SQL_DELETED_DETAIL = "DELETE FROM details WHERE id = ?";

}
