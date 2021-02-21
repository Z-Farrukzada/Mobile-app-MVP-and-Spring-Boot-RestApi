package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.AnnouncementDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.AnnouncementModelBrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.AnnouncementWithModelBrandExc;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
@Transactional
public class AnnouncementServicesImpl implements AnnouncementServices{

    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public List<Map<String, Object>> getWithModelBrandAndExchange() throws IOException {
        log.debug("Əsas ekran ucun elanlar cagirildi");
        List<Map<String,Object>> mainScreenAnnouncement = mainAnnouncementList(announcementRepository.getAnnouncementOnlyModelBrandExchange());
        log.debug("Elanlar listi " + mainScreenAnnouncement);
        return mainScreenAnnouncement;
    }

    private List<Map<String, Object>> mainAnnouncementList(List<AnnouncementWithModelBrandExc> announcementOnlyModelBrandExchange) throws IOException {
        log.debug("Elandaki sekil Base 64 - e cevrilir");
        List<Map<String, Object>> newdata = new ArrayList<>();
        for(AnnouncementWithModelBrandExc data : announcementOnlyModelBrandExchange){
            Map<String, Object> map = new HashMap<>();
            map.put("id",data.getId());
            map.put("walk",data.getWalk());
            map.put("price",data.getPrice());
            map.put("carYear",data.getCarYear());
            map.put("model_name",data.getModel_name());
            map.put("brand_name",data.getBrand_name());
            map.put("exchange_name",data.getExchange_name());
            map.put("imageUrl", Convert.ConvertBase64("image/" + data.getImageUrl()));
            newdata.add(map);
        }
        log.debug("Elandaki sekiller cevirildi..");
        return newdata;
    }

    @Override
    public List<AnnouncementDTO> getAll() throws IOException {
        return null;
    }

    @Override
    public AnnouncementDTO findById(int id) {
        return null;
    }

    @Override
    public Map<String, String> add(AnnouncementDTO entity) {
        return null;
    }

    @Override
    public Map<String, String> update(AnnouncementDTO entity) {
        return null;
    }

    @Override
    public Map<String, String> delete(int id) {
        return null;
    }
}
