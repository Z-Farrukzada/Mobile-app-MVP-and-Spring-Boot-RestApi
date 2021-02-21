package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.AnnouncementWithModelBrandExc;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarAnnouncement;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository.IBaseRepository;

import java.util.List;

public interface AnnouncementRepository extends IBaseRepository<CarAnnouncement> {

    List<AnnouncementWithModelBrandExc> getAnnouncementOnlyModelBrandExchange();

}
