package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.FuelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.FuelRepository;
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
public class FuelTypeServiceImpl implements  FuelTypeService{

    @Autowired
    FuelRepository fuelRepository;

    @Override
    public List<FuelDTO> getAll() {
        log.debug("Butun Yanacaqlar cagirilir...");
        List<FuelDTO> fuelDto = new ArrayList<>();
        for (CarFuelType fuel : fuelRepository.getAll()) {
            fuelDto.add(FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build());
        }
        log.debug("Butun Yanacaqlar  " + fuelDto);
        return fuelDto;
    }

    @Override
    public FuelDTO findById(int id) {
        log.debug("Yanacaq id-ye gore axtarilir...");
        CarFuelType fuel = fuelRepository.findById(id);
        log.debug("Yanacaq " + fuel);
        return FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build();
    }

    @Override
    public Map<String,String> add(FuelDTO entity){
        log.debug("Yeni yanacaq elave olunur...");
        Map<String,String> map = new HashMap<>();
            fuelRepository.add(addFuel(entity));
            map.put("Message","Yeni yanacaq yaradildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public  Map<String,String>  update(FuelDTO entity) {
        log.debug("Yanacaq deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            fuelRepository.update(addFuel(entity));
            map.put("Message","Yanacaq deyisdirildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        log.debug("Yanacaq silinir...");
        Map<String,String> map = new HashMap<>();
            fuelRepository.delete(id);
            map.put("Message","Yanacaq silindi");
        log.debug(map.get("Message"));
        return map;

    }

    public CarFuelType addFuel(FuelDTO fuelDTO){
        log.debug("FuelDTO CarFuelType-e elave olunur...");
        CarFuelType carFuelType =null;
        if(fuelDTO.getName() != null){
            carFuelType = CarFuelType.builder()
                    .id(fuelDTO.getId())
                    .name(fuelDTO.getName())
                    .build();
        }
        log.debug("FuelDTO CarFuelType-e elave olundu " + carFuelType);
        return carFuelType;
    }
}
