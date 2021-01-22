package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarDetail {
    private int id;
    private boolean hatch;
    private  boolean ABS;
    private  boolean airCondition;
}
