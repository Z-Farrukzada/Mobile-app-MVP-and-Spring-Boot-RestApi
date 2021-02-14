package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.DetailDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.DetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/details")
public class DetailController {

    @Autowired
    DetailService detailService;

    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllDetailCache")
    public List<DetailDTO> allListCarDetail(){
        return detailService.getAll();
    }


    @GetMapping("{detailId}")
    @Cacheable(cacheNames = "DetailCache")
    public DetailDTO findByIdDetail(@PathVariable("detailId") int detailId){
        return  detailService.findById(detailId);
    }


    @PostMapping("/created")
    @Caching(
            put= {@CachePut(cacheNames = "DetailCache", key = "#detailDTO.id")},
            evict = {@CacheEvict(cacheNames = "AllDetailCache",allEntries = true)}
    )
    public  Map<String,String> createdNewDetails(@RequestBody DetailDTO detailDTO){
        return detailService.add(detailDTO);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "DetailCache", key = "#detailDTO.id")},
            evict = {@CacheEvict(cacheNames = "AllDetailCache",allEntries = true)}
    )
    public Map<String,String> updatedDetail(@RequestBody DetailDTO detailDTO){
        return  detailService.update(detailDTO);
    }


    @DeleteMapping("{detailId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "DetailCache",key = "#detailId"),
                    @CacheEvict(cacheNames = "AllDetailCache",allEntries = true)
            }
    )
    public  Map<String,String> deletedDetail(@PathVariable("detailId") int detailId){
        return  detailService.delete(detailId);
    }
}
