package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarFuelType implements Serializable {
    private  int id;
    private  String name;
}
