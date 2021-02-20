package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;


import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.SlideDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/slide")
public class SlideController {

    @Autowired
    SliderService sliderService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "AllSlideCache")
    public List<Map<String,Object>> allListBrand() throws IOException {
        return slideList(sliderService.getAll());
    }

    private List<Map<String, Object>> slideList(List<SlideDTO> all) throws IOException {
        List<Map<String,Object>> newdata = new ArrayList<>();
        for(SlideDTO slide:all){
            Map<String,Object> map = new HashMap<>();
            map.put("id",slide.getId());
            map.put("imageUrl", Convert.ConvertBase64("image/" + slide.getImageUrl()));
            newdata.add(map);
        }
        return newdata;
    }


}
