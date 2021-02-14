package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestApiCarMobileAppSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiCarMobileAppSpringBootApplication.class, args);
	}



}
