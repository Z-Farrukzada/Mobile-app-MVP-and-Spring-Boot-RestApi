package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CarBrandWithModelCount implements Serializable {

    private  int id;
    private String name;
    private String logoImage;
    private  int count;

}
