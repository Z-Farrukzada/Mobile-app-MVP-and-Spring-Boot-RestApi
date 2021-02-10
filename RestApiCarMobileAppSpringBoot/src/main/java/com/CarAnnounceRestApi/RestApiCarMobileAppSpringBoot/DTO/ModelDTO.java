package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelDTO {
    private  int id;
    private  String name;
    private  int brandId;
}
