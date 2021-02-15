package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CityDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class CityServiceImpl implements  CityService{


    @Autowired
    CityRepository cityRepository;

    @Override
    public List<CityDTO> getAll() {
        log.debug("Butun Şəhərlər cagirildi...");
        List<CityDTO> cityDTOS = new ArrayList<>();
        for (City city :cityRepository.getAll()) {
            cityDTOS.add(CityDTO.builder().id(city.getId()).Name(city.getName()).build());
        }
        log.debug("Butun Şəhərlər " + cityDTOS);
        return cityDTOS;
    }

    @Override
    public CityDTO findById(int id)
    {
        log.debug("Şəhər id-ye gore axtarilir...");
        City city = cityRepository.findById(id);
        log.debug("Şəhər id-ye " + city);
        return CityDTO.builder().id(city.getId()).Name(city.getName()).build();
    }

    @Override
    public Map<String,String> add(CityDTO entity){
        log.debug("Şəhər yaradılir...");
        Map<String,String> map = new HashMap<>();
            cityRepository.add(addCity(entity));
            map.put("Message","Şəhər yaradıldı");
        log.debug(map.get("Message"));
        return map;

    }

    @Override
    public Map<String,String> update(CityDTO entity) {
        log.debug("Şəhər dəyisdirilir...");
        Map<String,String> map = new HashMap<>();
            cityRepository.update(addCity(entity));
            map.put("Message","Şəhər dəyisdirildi");
        log.debug(map.get("Message"));
        return map;

    }

    @Override
    public  Map<String,String>  delete(int id) {
        log.debug("Şəhər silinir...");
        Map<String,String> map = new HashMap<>();
            cityRepository.delete(id);
            map.put("Message","Şəhər silindi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Long WithNameFindCityId(String cityName) {
        return cityRepository.WithNameFindId(cityName);
    }


    public City addCity(CityDTO cityDTO){
        log.debug("CityDTO City-e elave olunur...");
        City city =null;
        if(cityDTO.getName() != null){
            city = City.builder()
                    .id(cityDTO.getId())
                    .Name(cityDTO.getName())
                    .build();
        }
        log.debug("CityDTO City-e elave olundu");
        return city;
    }
}
