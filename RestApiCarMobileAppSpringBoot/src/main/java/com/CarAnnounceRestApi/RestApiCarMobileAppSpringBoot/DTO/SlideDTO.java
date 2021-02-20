package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideDTO {

    private  int id;
    private  String imageUrl;

}
