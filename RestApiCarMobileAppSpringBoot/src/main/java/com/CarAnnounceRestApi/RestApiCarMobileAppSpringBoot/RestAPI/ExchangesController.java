package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exchange")
public class ExchangesController {

    @Autowired
    ExchangeService exchangeService;


    @GetMapping
    public ResponseEntity<List<Exchanges>> allListExchanges(){
        return new ResponseEntity<>(exchangeService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{exchangeId}")
    public ResponseEntity<Exchanges> findByIdExchange(@PathVariable("exchangeId") int exchangeId){
        return  new ResponseEntity<>(exchangeService.findById(exchangeId),HttpStatus.OK);
    }
    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createNewExchange(@RequestBody Exchanges exchanges){
        exchangeService.add(exchanges);
        Map<String,String> map = new HashMap<>();
        map.put("New Exchange","Created");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateExchange(@RequestBody Exchanges exchanges){
        exchangeService.update(exchanges);
        Map<String,String> map = new HashMap<>();
        map.put("Exchange","Updated");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/{exchangeId}")
    public ResponseEntity<Map<String,String>> deleteExchange(@PathVariable("exchangeId") int exchangeId){
        exchangeService.delete(exchangeId);
        Map<String,String> map = new HashMap<>();
        map.put("Exchange","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }
}
