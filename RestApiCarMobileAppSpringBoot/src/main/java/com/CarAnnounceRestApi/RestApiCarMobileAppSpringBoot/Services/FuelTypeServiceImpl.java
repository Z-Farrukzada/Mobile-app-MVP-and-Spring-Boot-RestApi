package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.FuelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FuelTypeServiceImpl implements  FuelTypeService{

    @Autowired
    FuelRepository fuelRepository;



    @Override
    public List<FuelDTO> getAll() {
        List<FuelDTO> fuelDto = new ArrayList<>();
        for (CarFuelType fuel : fuelRepository.getAll()) {
            fuelDto.add(FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build());
        }
        return fuelDto;
    }

    @Override
    public FuelDTO findById(int id) {
        CarFuelType fuel = fuelRepository.findById(id);
        return FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build();
    }

    @Override
    public Map<String,String> add(FuelDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try {
            fuelRepository.add(addFuel(entity));
            map.put("Message","Yeni yanacaq yaradildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yeni Yanacaq yaradilmadi !!!")));
        }
        return map;
    }

    @Override
    public  Map<String,String>  update(FuelDTO entity) {
        Map<String,String> map = new HashMap<>();
        try {
            fuelRepository.update(addFuel(entity));
            map.put("Message","Yanacaq deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yanacaq deyisidirlmedi")));
        }
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try {
            fuelRepository.delete(id);
            map.put("Message","Yanacaq silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yanacaq silinmedi")));
        }
        return map;

    }

    public CarFuelType addFuel(FuelDTO fuelDTO){
        CarFuelType carFuelType =null;
        if(fuelDTO.getName() != null){
            carFuelType = CarFuelType.builder()
                    .id(fuelDTO.getId())
                    .name(fuelDTO.getName())
                    .build();
        }
        return carFuelType;
    }
}
