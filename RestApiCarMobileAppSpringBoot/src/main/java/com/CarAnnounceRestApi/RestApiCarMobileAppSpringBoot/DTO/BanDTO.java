package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BanDTO  implements  Serializable{
    private  int id;
    private String name;
}
