package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    private int id;
    private String comment;
    private Date writetime;
    private int userId;
    private int announcementId;
}
