package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarModel {
    private  int id;
    private  String name;
    private  int brandId;
}
