package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FuelTypeServiceImpl implements  FuelTypeService{

    @Autowired
    FuelRepository fuelRepository;


    @Override
    public List<CarFuelType> getAll() {
        return fuelRepository.getAll();
    }

    @Override
    public CarFuelType findById(int id) {
        return fuelRepository.findById(id);
    }

    @Override
    public void add(CarFuelType entity) {
       fuelRepository.add(entity);
    }

    @Override
    public void update(CarFuelType entity) {
       fuelRepository.update(entity);
    }

    @Override
    public void delete(int id) {
       fuelRepository.delete(id);
    }
}
