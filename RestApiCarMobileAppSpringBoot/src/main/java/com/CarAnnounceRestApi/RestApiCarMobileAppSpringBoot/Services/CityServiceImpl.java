package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements  CityService{


    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public void add(City entity) {

    }

    @Override
    public void update(City entity) {

    }

    @Override
    public void delete(int id) {

    }
}
