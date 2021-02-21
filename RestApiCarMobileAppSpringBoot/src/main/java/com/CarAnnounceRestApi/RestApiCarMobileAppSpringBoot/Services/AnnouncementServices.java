package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.AnnouncementDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.AnnouncementModelBrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AnnouncementServices extends IBaseService<AnnouncementDTO> {
    List<Map<String,Object>> getWithModelBrandAndExchange() throws IOException;
}
