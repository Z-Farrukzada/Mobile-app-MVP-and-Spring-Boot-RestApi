package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private int id;
    private String comment;
    private LocalDateTime writeCommentDate;
    private int userId;
    private int announcementId;
}
