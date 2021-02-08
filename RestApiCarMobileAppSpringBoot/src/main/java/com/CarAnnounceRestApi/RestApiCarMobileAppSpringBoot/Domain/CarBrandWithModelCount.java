package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarBrandWithModelCount {

    private  int id;
    private String name;
    private String logoImage;
    private  int count;

}
