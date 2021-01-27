package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FuelQuery {

    public String  SQL_ALL_LIST_FUELS = "SELECT * FROM fuels";
    public String  SQL_FIND_By_ID = "SELECT * FROM fuels WHERE id= ?";
    public String  SQL_CREATED_FUEL = "INSERT INTO fuels(name) VALUES(?)";
    public String  SQL_UPDATED_FUEL="UPDATE fuels SET name = ? WHERE id = ?";
    public String  SQL_DELETED_FUEL = "DELETE FROM fuels WHERE id = ?";


}
