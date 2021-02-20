package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class BrandServicesImpl implements BrandServices {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAll() throws IOException {
        log.debug("Markalar cagirilidi");
        List<BrandDTO> brandDto = new ArrayList<>();
        for (CarBrand brand : brandRepository.getAll()) {
            brandDto.add(BrandDTO.builder().id(brand.getId()).name(brand.getName()).logoImage(brand.getLogoImage()).build());
        }
        log.debug("Markalarin listi " + brandDto);
        return brandDto;
    }

    @Override
    public BrandDTO findById(int brandId) {
        log.debug("Marka id-ye gore axtarilir");
        CarBrand brand = brandRepository.findById(brandId);
        log.debug("Marka tapildi" + brand);
        return BrandDTO.builder().id(brand.getId()).name(brand.getName()).logoImage(brand.getLogoImage()).build();
    }

    @Override
    public  Map<String,String> add(BrandDTO brandDto){
            log.debug("Marka elave olunur");
        Map<String,String> map = new HashMap<>();
            brandRepository.add(addCarBrand(brandDto));
            map.put("Message","Əlavə olundu");
            log.debug(map.get("Message"));
       return map;
    }

    @Override
    public Map<String,String> update(BrandDTO brandDto)  {
        log.debug("Marka deyisdirilir");
        Map<String,String> map = new HashMap<>();
            brandRepository.update(addCarBrand(brandDto));
            map.put("Message","Marka dəyişdirildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> delete(int brandId){
        log.debug("Marka silinir");
        Map<String,String> map = new HashMap<>();
            brandRepository.delete(brandId);
            map.put("Message","Marka silindi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public List<Map<String, Object>> popularBrand() throws IOException {
        log.debug("Populyar Markalar cagirilidi");
        List<Map<String,Object>> allPopList = popListBrandService(brandRepository.popularBrandList());
        log.debug("Populyar Markalarin listi " + allPopList);
        return allPopList;
    }

    public List<Map<String, Object>> popListBrandService(List<CarBrandWithModelCount> carBrandWithModelCounts) throws IOException {
        log.debug("Markadaki sekiller Base64-e cevrilir");
        List<Map<String, Object>> newdata = new ArrayList<>();
        for (CarBrandWithModelCount carBrand : carBrandWithModelCounts) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", carBrand.getId());
            map.put("name", carBrand.getName());
            map.put("logoImage", Convert.ConvertBase64("image/logo/" + carBrand.getLogoImage()));
            map.put("count", carBrand.getCount());
            newdata.add(map);
        }
        log.debug("Markadaki sekiller Base64-e cevrildi  " +  newdata);
        return newdata;
    }

    public CarBrand addCarBrand(BrandDTO brandDto){
        log.debug("BrandDTO CarBranda elave olunur");
        CarBrand carBrand = null;
        if(brandDto.getName() != null && brandDto.getName() != null){
            carBrand = CarBrand.builder()
                    .id(brandDto.getId())
                    .name(brandDto.getName())
                    .logoImage(brandDto.getLogoImage())
                    .build();
        }
        log.debug("BrandDTO CarBranda cevrildi" + carBrand);
        return carBrand;
    }


}
