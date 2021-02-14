package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO implements  Serializable{
    private int id;
    private String comment;
    private Date writetime;
    private int userId;
    private int announcementId;
}
