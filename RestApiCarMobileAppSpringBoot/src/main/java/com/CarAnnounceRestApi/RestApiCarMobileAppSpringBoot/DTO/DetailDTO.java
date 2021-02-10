package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailDTO {
    private int id;
    private  boolean hatch;
    private  boolean ABS;
    private  boolean aircondition;
}
