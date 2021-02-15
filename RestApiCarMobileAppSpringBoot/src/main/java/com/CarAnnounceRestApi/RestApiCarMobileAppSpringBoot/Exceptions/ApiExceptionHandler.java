package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({CustomNotFoundException.class})
    public String notFound(){
        return  "Istediyiniz netice tapilmadi";
    }

}
