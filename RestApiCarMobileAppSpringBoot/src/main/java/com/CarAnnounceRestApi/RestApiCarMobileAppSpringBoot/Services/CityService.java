package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;

public interface CityService extends IBaseService<City> {
    Long WithNameFindCityId(String cityName);
}
