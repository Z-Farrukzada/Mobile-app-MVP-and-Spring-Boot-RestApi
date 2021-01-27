package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailServiceImpl implements  DetailService{

    @Autowired
    DetailsRepository detailsRepository;

    @Override
    public List<CarDetail> getAll() {
        return detailsRepository.getAll();
    }

    @Override
    public CarDetail findById(int id) {
        return detailsRepository.findById(id);
    }

    @Override
    public void add(CarDetail entity) {
       detailsRepository.add(entity);
    }

    @Override
    public void update(CarDetail entity) {
       detailsRepository.update(entity);
    }

    @Override
    public void delete(int id) {
      detailsRepository.delete(id);
    }
}
