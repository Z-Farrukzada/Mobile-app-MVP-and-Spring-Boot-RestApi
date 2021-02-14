package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ExchangesDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ExchangeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(cacheNames = "AllExchangeCache")
    public  List<ExchangesDTO> allListExchanges() {
        return exchangeService.getAll();
    }

    @GetMapping("/{exchangeId}")
    @Cacheable(cacheNames = "ExchangeCache")
    public  ExchangesDTO findByIdExchange(@PathVariable("exchangeId") int exchangeId){
        return  exchangeService.findById(exchangeId);
    }

    @PostMapping("/created")
    @Caching(
            put= {@CachePut(cacheNames = "ExchangeCache", key = "#exchangesDto.id")},
            evict = {@CacheEvict(cacheNames = "AllExchangeCache",allEntries = true)}
    )
    public Map<String,String> createNewExchange(@RequestBody ExchangesDTO exchangesDto){
        return  exchangeService.add(exchangesDto);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "ExchangeCache", key = "#exchangesDto.id")},
            evict = {@CacheEvict(cacheNames = "AllExchangeCache",allEntries = true)}
    )
    public  Map<String,String>  updateExchange(@RequestBody ExchangesDTO exchangesDto){
        return  exchangeService.update(exchangesDto);
    }

    @DeleteMapping("/{exchangeId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ExchangeCache",key = "#exchangeId"),
                    @CacheEvict(cacheNames = "AllExchangeCache",allEntries = true)
            }
    )
    public Map<String,String> deleteExchange(@PathVariable("exchangeId") int exchangeId){
        return  exchangeService.delete(exchangeId);
    }

}
