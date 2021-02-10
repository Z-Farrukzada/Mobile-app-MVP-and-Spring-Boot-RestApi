package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CarColorsServiceImpl implements  CarColorsService{


    @Autowired
    CarColorsRepository carColorsRepository;

    @Override
    public List<ColorDTO> getAll() {
        List<ColorDTO> colorDto = new ArrayList<>();
        for (CarColors color : carColorsRepository.getAll()) {
            colorDto.add(ColorDTO.builder().id(color.getId()).name(color.getName()).build());
        }
        return colorDto;
    }

    @Override
    public ColorDTO findById(int id) {
        CarColors carColors = carColorsRepository.findById(id);
        return ColorDTO.builder().id(carColors.getId()).name(carColors.getName()).build();
    }

    @Override
    public Map<String,String> add(ColorDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try {
            carColorsRepository.add(addColor(entity));
            map.put("Message","Yeni reng yaradildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yeni Reng yaradilmadi")));
        }
         return map;
    }

    @Override
    public  Map<String,String> update(ColorDTO entity) throws CustomBadRequest{
        Map<String,String> map = new HashMap<>();
        try {
            carColorsRepository.update(addColor(entity));
            map.put("Message","Reng deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Reng deyisdirilmedi")));
        }
        return map;
    }

    @Override
    public Map<String,String> delete(int id) throws CustomBadRequest{
        Map<String,String> map = new HashMap<>();
        try{
            carColorsRepository.delete(id);
            map.put("Message","Reng silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Reng silinmedi")));
        }
       return map;
    }

    public CarColors addColor(ColorDTO colorDTO){
        CarColors carColors =null;
        if(colorDTO.getName() != null){
            carColors = CarColors.builder()
                    .id(colorDTO.getId())
                    .name(colorDTO.getName())
                    .build();
        }
        return carColors;
    }

}
