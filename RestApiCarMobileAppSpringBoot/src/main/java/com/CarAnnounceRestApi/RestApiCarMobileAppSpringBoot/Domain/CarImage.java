package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CarImage implements Serializable {
    private  int id;
    private  String image;

    private  int announcementId;
}
