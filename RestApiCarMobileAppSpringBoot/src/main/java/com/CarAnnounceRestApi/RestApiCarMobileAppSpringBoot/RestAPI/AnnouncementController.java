package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.AnnouncementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementServices announcementServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "AllAnnouncementModelBrandCache")
    public List<Map<String,Object>> allListAnnouncementWithModelBrand() throws IOException {
        return announcementServices.getWithModelBrandAndExchange();
    }

}
