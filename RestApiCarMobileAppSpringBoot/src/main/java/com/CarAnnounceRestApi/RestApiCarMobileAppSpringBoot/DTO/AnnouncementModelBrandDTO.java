package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnnouncementModelBrandDTO {

    private  int id;
    private  int walk;
    private  int price;
    private Date carYear;
    private String model_name;
    private String brand_name;
    private String exchange_name;
    private  Date createdAnnouncement;
    private  String imageUrl;

}
