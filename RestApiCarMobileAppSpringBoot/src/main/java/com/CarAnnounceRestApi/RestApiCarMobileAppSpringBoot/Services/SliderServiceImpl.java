package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.SlideDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarSlide;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.SliderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class SliderServiceImpl implements SliderService{

    @Autowired
    SliderRepository sliderRepository;


    @Override
    public List<SlideDTO> getAll() throws IOException {
        log.debug("Slayd cagirilidi");
        List<SlideDTO> sliderDto = new ArrayList<>();
        for (CarSlide slide : sliderRepository.getAll()) {
            sliderDto.add(SlideDTO.builder().id(slide.getId()).imageUrl(slide.getImageUrl()).build());
        }
        log.debug("Slayd listi " + sliderDto);
        return sliderDto;
    }

    @Override
    public SlideDTO findById(int id) {
        return null;
    }

    @Override
    public Map<String, String> add(SlideDTO entity) {
        return null;
    }

    @Override
    public Map<String, String> update(SlideDTO entity) {
        return null;
    }

    @Override
    public Map<String, String> delete(int id) {
        return null;
    }
}
