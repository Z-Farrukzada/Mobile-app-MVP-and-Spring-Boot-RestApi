package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository.IBaseRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BrandRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BrandServicesImpl  implements  BrandServices{

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<CarBrand> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public CarBrand findById(int brandId) {
        return  brandRepository.findById(brandId);
    }

    @Override
    public void add(CarBrand carBrand) {
        brandRepository.add(carBrand);
    }

    @Override
    public void update(CarBrand carBrand) {
        brandRepository.update(carBrand);
    }

    @Override
    public void delete(int brandId) {
        brandRepository.delete(brandId);
    }
}
