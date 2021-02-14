package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BrandServicesImpl implements BrandServices {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAll() throws IOException {
        List<BrandDTO> brandDto = new ArrayList<>();
        for (CarBrand brand : brandRepository.getAll()) {
            brandDto.add(BrandDTO.builder().id(brand.getId()).name(brand.getName()).logoImage(brand.getLogoImage()).build());
        }
        return brandDto;
    }

    @Override
    public BrandDTO findById(int brandId) {
        CarBrand brand = brandRepository.findById(brandId);
        return BrandDTO.builder().id(brand.getId()).name(brand.getName()).logoImage(brand.getLogoImage()).build();
    }

    @Override
    public  Map<String,String> add(BrandDTO brandDto) throws CustomBadRequest{
        Map<String,String> map = new HashMap<>();
        try{
            brandRepository.add(addCarBrand(brandDto));
            map.put("Message","Əlavə olundu");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Əlavə olunmadi")));
        }
       return map;
    }

    @Override
    public Map<String,String> update(BrandDTO brandDto) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            brandRepository.update(addCarBrand(brandDto));
            map.put("Message","Marka dəyişdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Marka dəyişdirilmədi")));
        }
        return map;

    }

    @Override
    public Map<String,String> delete(int brandId) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            brandRepository.delete(brandId);
            map.put("Message","Marka silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Marka silinmedi")));
        }
       return map;
    }

    @Override
    public List<Map<String, Object>> popularBrand() throws IOException {
        return popListBrandService(brandRepository.popularBrandList());
    }

    public List<Map<String, Object>> popListBrandService(List<CarBrandWithModelCount> carBrandWithModelCounts) throws IOException {
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

    public CarBrand addCarBrand(BrandDTO brandDto){
        CarBrand carBrand = null;
        if(brandDto.getName() != null && brandDto.getName() != null){
            carBrand = CarBrand.builder()
                    .id(brandDto.getId())
                    .name(brandDto.getName())
                    .logoImage(brandDto.getLogoImage())
                    .build();
        }
        return carBrand;
    }


}
