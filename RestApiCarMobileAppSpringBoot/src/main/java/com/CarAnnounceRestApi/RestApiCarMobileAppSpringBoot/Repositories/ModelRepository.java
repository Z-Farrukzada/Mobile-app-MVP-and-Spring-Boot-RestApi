package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomNotFoundException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository.IBaseRepository;
import java.util.List;


public interface ModelRepository  extends IBaseRepository<CarModel> {

   List<CarModel> FindModelByBrand(int id) ;

}
