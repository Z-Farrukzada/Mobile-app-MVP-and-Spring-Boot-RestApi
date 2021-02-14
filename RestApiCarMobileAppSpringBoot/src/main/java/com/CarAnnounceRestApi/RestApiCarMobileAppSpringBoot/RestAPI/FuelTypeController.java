package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.FuelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.DetailService;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.FuelTypeService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fuels")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllFuelsCache")
    public List<FuelDTO> allListCarFuels(){
        return  fuelTypeService.getAll();
    }


    @GetMapping("{fuelId}")
    @Cacheable(cacheNames = "FuelCache")
    public FuelDTO findByIdFuel(@PathVariable("fuelId") int fuelId){
        return  fuelTypeService.findById(fuelId);
    }

    @PostMapping("/created")
    @Caching(
            put= {@CachePut(cacheNames = "FuelCache", key = "#fuelDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllFuelsCache",allEntries = true)}
    )
    public Map<String,String> createdNewFuel(@RequestBody FuelDTO fuelDTO){
        return fuelTypeService.add(fuelDTO);
    }


    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "FuelCache", key = "#fuelDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllFuelsCache",allEntries = true)}
    )
    public Map<String,String> updatedFuel(@RequestBody FuelDTO fuelDTO){
        return  fuelTypeService.update(fuelDTO);
    }


    @DeleteMapping("/{fuelId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "FuelCache",key = "#fuelId"),
                    @CacheEvict(cacheNames = "AllFuelsCache",allEntries = true)
            }
    )
    public Map<String,String> deletedDetail(@PathVariable("fuelId") int fuelId){
        return fuelTypeService.delete(fuelId);
    }

}
