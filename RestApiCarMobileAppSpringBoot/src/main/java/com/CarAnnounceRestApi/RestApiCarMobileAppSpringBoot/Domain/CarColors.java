package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarColors{
    private  int id;
    private String name;
}
