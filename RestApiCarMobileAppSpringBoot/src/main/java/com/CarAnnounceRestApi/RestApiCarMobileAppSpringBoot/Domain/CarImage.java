package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CarImage {
    private  int id;
    private  String image;

    private  int announcementId;
}
