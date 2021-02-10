package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CityDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CityServiceImpl implements  CityService{


    @Autowired
    CityRepository cityRepository;

    @Override
    public List<CityDTO> getAll() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        for (City city :cityRepository.getAll()) {
            cityDTOS.add(CityDTO.builder().id(city.getId()).Name(city.getName()).build());
        }
        return cityDTOS;
    }

    @Override
    public CityDTO findById(int id)
    {
        City city = cityRepository.findById(id);
        return CityDTO.builder().id(city.getId()).Name(city.getName()).build();
    }

    @Override
    public Map<String,String> add(CityDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            cityRepository.add(addCity(entity));
            map.put("Message","Şəhər yaradıldı");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Şəhər yaradilmadi")));
        }
        return map;

    }

    @Override
    public Map<String,String> update(CityDTO entity) {
        Map<String,String> map = new HashMap<>();
        try{
            cityRepository.update(addCity(entity));
            map.put("Message","Şəhər dəyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Şəhər dəyisdirilmedi")));
        }
        return map;

    }

    @Override
    public  Map<String,String>  delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            cityRepository.delete(id);
            map.put("Message","Şəhər silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Şəhər silinmedi")));
        }
        return map;
    }

    @Override
    public Long WithNameFindCityId(String cityName) {
        return cityRepository.WithNameFindId(cityName);
    }


    public City addCity(CityDTO cityDTO){
        City city =null;
        if(cityDTO.getName() != null){
            city = City.builder()
                    .id(cityDTO.getId())
                    .Name(cityDTO.getName())
                    .build();
        }
        return city;
    }
}
