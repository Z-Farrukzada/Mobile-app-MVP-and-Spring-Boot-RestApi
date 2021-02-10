package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ExchangesDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ExchangeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exchange")
public class ExchangesController {

    @Autowired
    ExchangeService exchangeService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<ExchangesDTO>> allListExchanges() {
        return new ResponseEntity<>(exchangeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{exchangeId}")
    public ResponseEntity<ExchangesDTO> findByIdExchange(@PathVariable("exchangeId") int exchangeId){
        return  new ResponseEntity<>(exchangeService.findById(exchangeId),HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createNewExchange(@RequestBody ExchangesDTO exchangesDto){
        return new ResponseEntity<>(exchangeService.add(exchangesDto),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateExchange(@RequestBody ExchangesDTO exchangesDto){
        return new ResponseEntity<>(exchangeService.update(exchangesDto),HttpStatus.OK);
    }

    @DeleteMapping("/{exchangeId}")
    public ResponseEntity<Map<String,String>> deleteExchange(@PathVariable("exchangeId") int exchangeId){
        return  new ResponseEntity<>(exchangeService.delete(exchangeId),HttpStatus.OK);
    }

}
