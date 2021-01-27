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
    public List<City> getAll() throws CustomNotFoundException {
        try{
            return  jdbcTemplate.query(CityQuery.SQL_ALL_CITY_LIST,cityRowMapper);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public City findById(int id) throws CustomNotFoundException {
        try{
            return  jdbcTemplate.queryForObject(CityQuery.SQL_FIND_BY_ID_CITY,cityRowMapper,id);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public void add(City entity) throws CustomBadRequest {
        try{
           jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(CityQuery.SQL_CREATED_CITY, Statement.NO_GENERATED_KEYS);
               ps.setString(1,entity.getName());
               return ps;
           });
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(City entity) throws CustomBadRequest {
        try{
            jdbcTemplate.update(CityQuery.SQL_UPDATED_CITY,entity.getName(),entity.getId());
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) throws CustomNotFoundException {
        try{
            jdbcTemplate.update(CityQuery.SQL_DELETED_CITY,id);
        }catch (Exception e){
            throw  new CustomBadRequest(e.getLocalizedMessage());
        }
    }
    @Override
    public Long WithNameFindId(String cityName) throws CustomNotFoundException {
        try{
            return  jdbcTemplate.queryForObject(CityQuery.SQL_WITH_NAME_FIND_ID,Long.class,cityName);
        }catch (Exception e){
            throw  new CustomNotFoundException(e.getLocalizedMessage());
        }
    }

    public RowMapper<City> cityRowMapper = (((resultSet, i) -> {
        return  new City(resultSet.getInt("id"),
                resultSet.getString("name"));
    }));



}
