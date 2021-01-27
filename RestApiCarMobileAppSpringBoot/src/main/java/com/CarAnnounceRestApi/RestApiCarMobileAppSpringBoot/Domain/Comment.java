package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String comment;
    private Date writetime;
    private int userId;
    private int announcementId;
}
