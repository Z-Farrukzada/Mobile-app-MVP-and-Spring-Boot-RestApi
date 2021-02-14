package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarDetail implements Serializable {
    private int id;
    private  boolean hatch;
    private  boolean ABS;
    private  boolean aircondition;
}
