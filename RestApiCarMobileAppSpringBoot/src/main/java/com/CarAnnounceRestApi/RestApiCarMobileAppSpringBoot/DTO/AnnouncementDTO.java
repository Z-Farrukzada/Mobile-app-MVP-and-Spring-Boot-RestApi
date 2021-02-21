package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AnnouncementDTO {

    private  int id;
    private  int walk;
    private  int price;
    private  boolean credit;
    private  boolean barter;
    private  String description;
    private  String transmission;
    private  String gearBox;
    private DateFormat carYear;
    private  int engineCapacity ;
    private  int enginePower;

    private  int modelId;
    private  int fuelId;
    private  int colorId;
    private  int detailId;
    private  int banId;
    private  int userId;
    private  int exchangeId;
    private  DateFormat createdAnnouncement;
    private  DateFormat updatedAnnouncement;
}
