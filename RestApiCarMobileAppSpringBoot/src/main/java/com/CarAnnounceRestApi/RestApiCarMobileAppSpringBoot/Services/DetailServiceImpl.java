package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.DetailDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DetailServiceImpl implements  DetailService{

    @Autowired
    DetailsRepository detailsRepository;

    @Override
    public List<DetailDTO> getAll() {
        List<DetailDTO> detailDto = new ArrayList<>();
        for (CarDetail detail : detailsRepository.getAll()) {
            detailDto.add(DetailDTO.builder()
                    .id(detail.getId())
                    .ABS(detail.isABS())
                    .aircondition(detail.isAircondition())
                    .hatch(detail.isHatch())
                    .build());
        }
        return detailDto;
    }

    @Override
    public DetailDTO findById(int id) {
        CarDetail detail = detailsRepository.findById(id);
        return DetailDTO.builder()
                .id(detail.getId())
                .hatch(detail.isHatch())
                .aircondition(detail.isAircondition())
                .ABS(detail.isABS())
                .build();
    }

    @Override
    public Map<String,String> add(DetailDTO entity) {
        Map<String,String> map = new HashMap<>();
        try {
            detailsRepository.add(addDetail(entity));
            map.put("Message","Yeni detallar yaradildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yeni detal yaradilmadi")));
        }
        return map;

    }

    @Override
    public Map<String,String> update(DetailDTO entity) {
        Map<String,String> map = new HashMap<>();
        try {
            detailsRepository.update(addDetail(entity));
            map.put("Message","Detallar deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Detallar deyisdirilmedi !!!")));
        }
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            detailsRepository.delete(id);
            map.put("Message","Detal silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Detal silinmedi")));
        }
        return map;

    }

    public CarDetail addDetail(DetailDTO detailDTO){
        CarDetail carDetail =null;
        if(detailDTO.isABS() && detailDTO.isAircondition() && detailDTO.isHatch()){
            carDetail = CarDetail.builder()
                    .id(detailDTO.getId())
                    .ABS(detailDTO.isABS())
                    .aircondition(detailDTO.isAircondition())
                    .hatch(detailDTO.isHatch())
                    .build();
        }
        return carDetail;
    }

}
