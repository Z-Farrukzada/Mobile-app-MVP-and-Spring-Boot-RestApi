package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;


import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<City> getAll() throws CustomNotFoundException {
        return null;
    }

    @Override
    public City findById(int id) throws CustomNotFoundException {
        return null;
    }

    @Override
    public void add(City entity) throws CustomBadRequest {

    }

    @Override
    public void update(City entity) throws CustomBadRequest {

    }

    @Override
    public void delete(int id) throws CustomNotFoundException {

    }
}
