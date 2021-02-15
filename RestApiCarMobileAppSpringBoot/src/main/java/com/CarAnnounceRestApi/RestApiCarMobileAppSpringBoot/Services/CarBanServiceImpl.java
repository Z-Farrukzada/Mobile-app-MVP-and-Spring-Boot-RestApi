package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.CarBanRepository;
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
public class CarBanServiceImpl implements  CarBanService{

    @Autowired
    CarBanRepository carBanRepository;

    @Override
    public List<BanDTO> getAll() {
        log.debug("Butun Masin Banlari cagirilidi");
        List<BanDTO> banDto = new ArrayList<>();
        for (CarBan ban : carBanRepository.getAll()) {
            banDto.add(BanDTO.builder().id(ban.getId()).name(ban.getName()).build());
        }
        log.debug("Butun Masin Banlari " + banDto);
        return banDto;
    }

    @Override
    public BanDTO findById(int id) {
        log.debug("Ban id-ye gore axtarilir");
        CarBan carBan = carBanRepository.findById(id);
        log.debug("Ban tapildi " + carBan);
        return BanDTO.builder().id(carBan.getId()).name(carBan.getName()).build();
    }

    @Override
    public Map<String,String> add(BanDTO entity){
        log.debug("Yeni Masin Bani elave olunur");
        Map<String,String> map = new HashMap<>();
            carBanRepository.add(addCarBan(entity));
            map.put("Message","Ban yaradıldı");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> update(BanDTO entity){
        log.debug("Masin Bani deyisdirilir");
        Map<String,String> map = new HashMap<>();
           carBanRepository.update(addCarBan(entity));
           map.put("Message","Ban deyisdirildi");
        log.debug(map.get("Message"));
      return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        log.debug("Masin Bani silinir");
        Map<String,String> map = new HashMap<>();
            carBanRepository.delete(id);
            map.put("Message","Ban silindi");
        log.debug(map.get("Message"));
        return map;
    }

    public CarBan addCarBan(BanDTO banDTO){
        log.debug("BanDT0 CarBan-a elave olunur");
        CarBan carBan =null;
        if(banDTO.getName() != null){
          carBan = CarBan.builder()
                    .id(banDTO.getId())
                    .name(banDTO.getName())
                    .build();
        }
        log.debug("BanDT0 CarBan-a elave olundu " + carBan);
        return carBan;
    }
}
