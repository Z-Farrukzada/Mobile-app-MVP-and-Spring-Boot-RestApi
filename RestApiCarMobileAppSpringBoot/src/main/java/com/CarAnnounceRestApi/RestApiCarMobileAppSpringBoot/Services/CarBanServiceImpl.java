package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarBanServiceImpl implements  CarBanService{

    @Autowired
    CarBanRepository carBanRepository;

    @Override
    public List<CarBan> getAll() {
        return carBanRepository.getAll();
    }
    @Override
    public CarBan findById(int id) {
        return carBanRepository.findById(id);
    }
    @Override
    public void add(CarBan entity) {
        carBanRepository.add(entity);
    }
    @Override
    public void update(CarBan entity) {
      carBanRepository.update(entity);
    }
    @Override
    public void delete(int id) {
      carBanRepository.delete(id);
    }
}
