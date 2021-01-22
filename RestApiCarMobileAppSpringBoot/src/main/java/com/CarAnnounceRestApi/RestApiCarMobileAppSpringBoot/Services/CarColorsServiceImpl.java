package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarColorsServiceImpl implements  CarColorsService{

    @Autowired
    CarColorsRepository carColorsRepository;

    @Override
    public List<CarColors> getAll() {
        return carColorsRepository.getAll();
    }

    @Override
    public CarColors findById(int id) {
        return carColorsRepository.findById(id);
    }

    @Override
    public void add(CarColors entity) {
         carColorsRepository.add(entity);
    }

    @Override
    public void update(CarColors entity) {
         carColorsRepository.update(entity);
    }

    @Override
    public void delete(int id) {
      carColorsRepository.delete(id);
    }
}
