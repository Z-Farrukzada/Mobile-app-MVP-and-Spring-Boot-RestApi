package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;


import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.CityQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<City> getAll() {
            return  jdbcTemplate.query(CityQuery.SQL_ALL_CITY_LIST,cityRowMapper);
    }

    @Override
    public City findById(int id) throws CustomNotFoundException {
            return  jdbcTemplate.queryForObject(CityQuery.SQL_FIND_BY_ID_CITY,cityRowMapper,id);
    }

    @Override
    public void add(City entity) throws CustomBadRequest {
           jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(CityQuery.SQL_CREATED_CITY, Statement.NO_GENERATED_KEYS);
               ps.setString(1,entity.getName());
               return ps;
           });

    }

    @Override
    public void update(City entity) throws CustomBadRequest {
            jdbcTemplate.update(CityQuery.SQL_UPDATED_CITY,entity.getName(),entity.getId());
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
            jdbcTemplate.update(CityQuery.SQL_DELETED_CITY,id);
    }
    @Override
    public Long WithNameFindId(String cityName) throws CustomNotFoundException {
            return  jdbcTemplate.queryForObject(CityQuery.SQL_WITH_NAME_FIND_ID,Long.class,cityName);
    }


    public RowMapper<City> cityRowMapper = (((resultSet, i) -> {
        return  new City(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));



}
