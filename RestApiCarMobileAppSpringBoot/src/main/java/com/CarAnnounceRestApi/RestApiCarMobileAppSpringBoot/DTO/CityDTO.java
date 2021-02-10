package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private int id;
    private String Name;

}
