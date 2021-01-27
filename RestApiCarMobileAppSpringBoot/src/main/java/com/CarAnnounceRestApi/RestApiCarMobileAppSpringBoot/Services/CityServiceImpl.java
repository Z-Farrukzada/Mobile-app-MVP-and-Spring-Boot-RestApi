package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements  CityService{


    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.getAll();
    }

    @Override
    public City findById(int id)
    {
        return cityRepository.findById(id);
    }

    @Override
    public void add(City entity) {
        cityRepository.add(entity);
    }

    @Override
    public void update(City entity) {
        cityRepository.update(entity);
    }

    @Override
    public void delete(int id) {
       cityRepository.delete(id);
    }

    @Override
    public Long WithNameFindCityId(String cityName) {
        return cityRepository.WithNameFindId(cityName);
    }
}
