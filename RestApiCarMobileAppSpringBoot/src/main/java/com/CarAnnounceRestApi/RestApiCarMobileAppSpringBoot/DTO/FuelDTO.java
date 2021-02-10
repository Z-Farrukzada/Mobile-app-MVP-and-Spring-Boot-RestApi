package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuelDTO {
    private  int id;
    private  String name;
}
