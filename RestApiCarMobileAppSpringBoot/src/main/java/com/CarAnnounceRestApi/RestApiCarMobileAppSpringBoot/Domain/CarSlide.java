package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarSlide {

    private  int id;
    private  String imageUrl;

}
