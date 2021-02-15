package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.DetailDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.DetailsRepository;
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
public class DetailServiceImpl implements  DetailService{

    @Autowired
    DetailsRepository detailsRepository;

    @Override
    public List<DetailDTO> getAll() {
        log.debug("Butun Detallar cagirilir...");
        List<DetailDTO> detailDto = new ArrayList<>();
        for (CarDetail detail : detailsRepository.getAll()) {
            detailDto.add(DetailDTO.builder()
                    .id(detail.getId())
                    .ABS(detail.isABS())
                    .aircondition(detail.isAircondition())
                    .hatch(detail.isHatch())
                    .build());
        }
        log.debug("Butun Detallar  " + detailDto);
        return detailDto;
    }

    @Override
    public DetailDTO findById(int id) {
        log.debug("Detal id-ye gore axtarilir...");
        CarDetail detail = detailsRepository.findById(id);
        log.debug("Detal  " + detail);
        return DetailDTO.builder()
                .id(detail.getId())
                .hatch(detail.isHatch())
                .aircondition(detail.isAircondition())
                .ABS(detail.isABS())
                .build();
    }

    @Override
    public Map<String,String> add(DetailDTO entity) {
        log.debug("Yeni detal elave olunur...");
        Map<String,String> map = new HashMap<>();
            detailsRepository.add(addDetail(entity));
            map.put("Message","Yeni detallar yaradildi");
        log.debug(map.get("Message"));
        return map;

    }

    @Override
    public Map<String,String> update(DetailDTO entity) {
        log.debug("Detal deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            detailsRepository.update(addDetail(entity));
            map.put("Message","Detallar deyisdirildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        log.debug("Detal silinir...");
        Map<String,String> map = new HashMap<>();
            detailsRepository.delete(id);
            map.put("Message","Detal silindi");
        log.debug(map.get("Message"));
        return map;

    }

    public CarDetail addDetail(DetailDTO detailDTO){
        log.debug("DetailDTO CarDetail-e elave olunur...");
        CarDetail carDetail =null;
        if(detailDTO.isABS() && detailDTO.isAircondition() && detailDTO.isHatch()){
            carDetail = CarDetail.builder()
                    .id(detailDTO.getId())
                    .ABS(detailDTO.isABS())
                    .aircondition(detailDTO.isAircondition())
                    .hatch(detailDTO.isHatch())
                    .build();
        }
        log.debug("DetailDTO CarDetail-e elave olundu " + carDetail);
        return carDetail;
    }

}
