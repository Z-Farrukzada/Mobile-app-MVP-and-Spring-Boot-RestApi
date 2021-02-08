package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface BrandServices extends IBaseService<CarBrand> {

    List<Map<String,Object>> popularBrand() throws IOException;

}
