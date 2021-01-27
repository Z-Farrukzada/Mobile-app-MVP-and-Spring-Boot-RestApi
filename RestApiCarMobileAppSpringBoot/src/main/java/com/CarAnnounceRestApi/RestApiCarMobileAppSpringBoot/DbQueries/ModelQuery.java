package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelQuery {

    public  String SQL_ALL_LIST_MODELS = "SELECT * FROM model";
    public  String SQL_FIND_BY_ID_MODEL = "SELECT * FROM model WHERE id = ?";
    public  String SQL_NEW_MODEL_CREATED = "INSERT INTO model(name,brandId) VALUES(?,?)";
    public  String SQL_UPDATE_MODEL = "UPDATE model SET name = ? , brandId = ? WHERE id = ?";
    public  String SQL_DELETED_MODEL = "DELETE FROM model  WHERE id = ?";

}
