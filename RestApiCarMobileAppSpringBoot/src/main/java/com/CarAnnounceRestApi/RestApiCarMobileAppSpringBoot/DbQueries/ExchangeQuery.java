package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExchangeQuery {

    public String SQL_ALL_LIST_EXCHANGES = "SELECT * FROM exchanges";
    public String SQL_FIND_By_ID = "SELECT * FROM exchanges WHERE id= ?";
    public String SQL_CREATED_EXCHANGE = "INSERT INTO exchanges(name) VALUES(?)";
    public String SQL_UPDATED_EXCHANGE="UPDATE exchanges SET name = ? WHERE id = ?";
    public String SQL_DELETED_EXCHANGE = "DELETE FROM exchanges WHERE id = ?";

}
