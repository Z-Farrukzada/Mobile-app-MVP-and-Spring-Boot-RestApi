package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelDTO implements  Serializable {
    private  int id;
    private  String name;
    private  int brandId;
}
