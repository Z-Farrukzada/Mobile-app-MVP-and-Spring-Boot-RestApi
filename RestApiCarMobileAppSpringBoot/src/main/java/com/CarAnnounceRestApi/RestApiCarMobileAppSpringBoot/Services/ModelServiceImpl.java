package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelServiceImpl implements  ModelService{

    @Autowired
    ModelRepository modelRepository;

    @Override
    public List<CarModel> getAll() {
        return modelRepository.getAll();
    }

    @Override
    public CarModel findById(int id) {
        return modelRepository.findById(id);
    }

    @Override
    public void add(CarModel entity) {
        modelRepository.add(entity);
    }

    @Override
    public void update(CarModel entity) {
      modelRepository.update(entity);
    }

    @Override
    public void delete(int id) {
      modelRepository.delete(id);
    }
}
