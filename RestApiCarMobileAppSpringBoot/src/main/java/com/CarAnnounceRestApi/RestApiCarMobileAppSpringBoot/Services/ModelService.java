package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BaseService.IBaseService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ModelService extends IBaseService<CarModel> {

   List<CarModel> FindByModelByBrandId(int id);

}
