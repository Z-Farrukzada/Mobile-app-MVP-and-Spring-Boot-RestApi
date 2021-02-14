package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("FuelDTO")
public class FuelDTO implements  Serializable{
    private  int id;
    private  String name;
}
