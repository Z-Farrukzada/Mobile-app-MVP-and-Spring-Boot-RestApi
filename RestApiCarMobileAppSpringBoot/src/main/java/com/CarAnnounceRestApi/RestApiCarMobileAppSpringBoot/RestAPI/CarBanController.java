package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarBanService;
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
@RequestMapping("api/ban")
public class CarBanController {

    @Autowired
    CarBanService carBanService;


    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllBanCache")
    public List<BanDTO> allListCarBans(){
        return carBanService.getAll();
    }

    @GetMapping("/{banId}")
    @Cacheable(cacheNames = "BanCache")
    public BanDTO findByIdCarBan(@PathVariable("banId") int id){
        return  carBanService.findById(id);
    }

    @PostMapping("/createdBan")
    @Caching(
            put= {@CachePut(cacheNames = "BanCache", key = "#banDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllBanCache",allEntries = true)}
    )
    public Map<String,String> createNewBan(@RequestBody BanDTO banDTO){
        return carBanService.add(banDTO);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "BanCache", key = "#banDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllBanCache",allEntries = true)}
    )
    public Map<String,String> updateColor(@RequestBody BanDTO banDTO){
        return carBanService.update(banDTO);
    }

    @DeleteMapping("{banId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "BanCache",key = "#id"),
                    @CacheEvict(cacheNames = "AllBanCache",allEntries = true)
            }
    )
    public Map<String,String> deleteColor(@PathVariable("banId") int id){
        return  carBanService.delete(id);
    }


}
