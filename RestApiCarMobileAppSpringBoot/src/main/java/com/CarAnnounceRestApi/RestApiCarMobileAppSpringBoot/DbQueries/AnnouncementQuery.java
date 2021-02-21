package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AnnouncementQuery {

    public String SQL_ANNOUNCEMENT_MAIN = "SELECT announcement.id,announcement.walk,announcement.price,announcement.year AS carYear,announcement.created AS createdAnnouncement," +
            "model.name AS model_name,brand.name AS brand_name,exchanges.name AS exchange_name , image.images AS imageUrl FROM announcement "  +
            "JOIN model  ON announcement.modelId = model.id JOIN brand  ON brand.id = model.brandId " +
            "JOIN exchanges  ON exchanges.id = announcement.exchangeId " +
            "JOIN image  ON image.announcementId = announcement.id GROUP BY announcement.id ORDER BY created ASC LIMIT 8";
}
