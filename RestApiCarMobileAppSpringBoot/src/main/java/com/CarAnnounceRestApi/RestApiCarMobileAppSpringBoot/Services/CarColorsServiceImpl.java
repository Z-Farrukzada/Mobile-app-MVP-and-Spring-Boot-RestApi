package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarColorsRepository;
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
public class CarColorsServiceImpl implements  CarColorsService{


    @Autowired
    CarColorsRepository carColorsRepository;

    @Override
    public List<ColorDTO> getAll() {
        log.debug("Butun Masin rengleri cagirildi");
        List<ColorDTO> colorDto = new ArrayList<>();
        for (CarColors color : carColorsRepository.getAll()) {
            colorDto.add(ColorDTO.builder().id(color.getId()).name(color.getName()).build());
        }
        log.debug("Butun Masin rengleri " + colorDto);
        return colorDto;
    }

    @Override
    public ColorDTO findById(int id) {
        log.debug("Masin Rengi id-ye gore axtarilir....");
        CarColors carColors = carColorsRepository.findById(id);
        log.debug("Masin Rengi " + carColors);
        return ColorDTO.builder().id(carColors.getId()).name(carColors.getName()).build();
    }

    @Override
    public Map<String,String> add(ColorDTO entity){
        log.debug("Masin rengi yaradillir...");
        Map<String,String> map = new HashMap<>();
            carColorsRepository.add(addColor(entity));
            map.put("Message","Yeni reng yaradildi");
        log.debug(map.get("Message"));
         return map;
    }

    @Override
    public  Map<String,String> update(ColorDTO entity){
        log.debug("Masin rengi deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            carColorsRepository.update(addColor(entity));
            map.put("Message","Reng deyisdirildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        log.debug("Masin rengi silinir...");
        Map<String,String> map = new HashMap<>();
            carColorsRepository.delete(id);
            map.put("Message","Reng silindi");
        log.debug(map.get("Message"));
       return map;
    }

    public CarColors addColor(ColorDTO colorDTO){
        log.debug("ColorDTO CarColor-a elave olunur...");
        CarColors carColors =null;
        if(colorDTO.getName() != null){
            carColors = CarColors.builder()
                    .id(colorDTO.getId())
                    .name(colorDTO.getName())
                    .build();
        }
        log.debug("ColorDTO CarColor-a elave olundu " + carColors);
        return carColors;
    }

}
