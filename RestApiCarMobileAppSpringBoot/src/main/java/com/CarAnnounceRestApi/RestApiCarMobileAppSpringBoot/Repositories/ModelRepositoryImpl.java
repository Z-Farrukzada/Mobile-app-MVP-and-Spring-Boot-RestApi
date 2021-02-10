package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.ModelQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.UserQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class ModelRepositoryImpl implements  ModelRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<CarModel> getAll(){
            return jdbcTemplate.query(ModelQuery.SQL_ALL_LIST_MODELS,carModelRowMapper);
    }

    @Override
    public CarModel findById(int id) throws CustomNotFoundException {
            return  jdbcTemplate.queryForObject(ModelQuery.SQL_FIND_BY_ID_MODEL,carModelRowMapper,id);
    }

    @Override
    public void add(CarModel entity) throws CustomBadRequest {
             jdbcTemplate.update(connection -> {
                 PreparedStatement ps = connection.prepareStatement(ModelQuery.SQL_NEW_MODEL_CREATED, Statement.RETURN_GENERATED_KEYS);
                 ps.setString(1,entity.getName());
                 ps.setInt(2,entity.getBrandId());
                 return ps;
             });
    }

    @Override
    public void update(CarModel entity) throws CustomBadRequest {
              jdbcTemplate.update(ModelQuery.SQL_UPDATE_MODEL,entity.getName(),entity.getBrandId(),entity.getId());
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
             jdbcTemplate.update(ModelQuery.SQL_DELETED_MODEL,id);
    }

    @Override
    public List<CarModel> FindModelByBrand(int id) throws CustomNotFoundException {
            return  jdbcTemplate.query(ModelQuery.SQL_SELECTED_MODEL_WITH_BRAND,carModelRowMapper,id);
    }

    public RowMapper<CarModel> carModelRowMapper = ((resultSet, i) -> {
         return  new CarModel(resultSet.getInt("id"),
                               resultSet.getString("name"),
                                resultSet.getInt("brandId"));
    });



}
