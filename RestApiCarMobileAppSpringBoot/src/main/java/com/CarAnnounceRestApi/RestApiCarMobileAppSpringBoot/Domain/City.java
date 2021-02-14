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
public class City implements Serializable {

    private int id;
    private String Name;

}
