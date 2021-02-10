package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CarBanServiceImpl implements  CarBanService{

    @Autowired
    CarBanRepository carBanRepository;

    @Override
    public List<BanDTO> getAll() {
        List<BanDTO> banDto = new ArrayList<>();
        for (CarBan ban : carBanRepository.getAll()) {
            banDto.add(BanDTO.builder().id(ban.getId()).name(ban.getName()).build());
        }
        return banDto;
    }

    @Override
    public BanDTO findById(int id) {
        CarBan carBan = carBanRepository.findById(id);
        return BanDTO.builder().id(carBan.getId()).name(carBan.getName()).build();
    }

    @Override
    public Map<String,String> add(BanDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try{
            carBanRepository.add(addCarBan(entity));
            map.put("Message","Ban yaradıldı");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Ban yaradilmadi")));
        }
        return map;
    }

    @Override
    public Map<String,String> update(BanDTO entity) throws CustomBadRequest{
        Map<String,String> map = new HashMap<>();
        try{
           carBanRepository.update(addCarBan(entity));
           map.put("Message","Ban deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Ban deyisdirilmedi")));
        }
      return map;
    }
    @Override
    public Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            carBanRepository.delete(id);
            map.put("Message","Ban silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Ban silinmedi")));
        }
        return map;
    }

    public CarBan addCarBan(BanDTO banDTO){
        CarBan carBan =null;
        if(banDTO.getName() != null){
          carBan = CarBan.builder()
                    .id(banDTO.getId())
                    .name(banDTO.getName())
                    .build();
        }
        return carBan;
    }
}
