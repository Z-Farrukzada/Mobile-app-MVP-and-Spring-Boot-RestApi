package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarModel implements Serializable {
    private  int id;
    private  String name;
    private  int brandId;
}
