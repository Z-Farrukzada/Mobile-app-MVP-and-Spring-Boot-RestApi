package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository.IBaseRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BrandRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BrandServicesImpl  implements  BrandServices{

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<CarBrand>getAll() throws IOException {
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

    @Override
    public List<Map<String,Object>> popularBrand() throws IOException {
       return  popListBrandService(brandRepository.popularBrandList());
    }

    public List<Map<String,Object>> popListBrandService(List<CarBrandWithModelCount> carBrandWithModelCounts) throws IOException {
        List<Map<String, Object>> newdata = new ArrayList<>();
        for (CarBrandWithModelCount carBrand : carBrandWithModelCounts) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", carBrand.getId());
            map.put("name", carBrand.getName());
            map.put("logoImage", Convert.ConvertBase64(carBrand.getLogoImage()));
            map.put("count", carBrand.getCount());
            newdata.add(map);
        }
        return newdata;
    }
}
