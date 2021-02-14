package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarColorsService;
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
@RequestMapping("api/carColors")
public class CarColorController {

    @Autowired
    CarColorsService carColorsService;


    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllColorCache")
    public List<ColorDTO> allListColors(){
         return  carColorsService.getAll();
    }


    @GetMapping("/{colorId}")
    @Cacheable(cacheNames = "ColorCache")
    public ColorDTO findByIdColor(@PathVariable("colorId") int colorId){
        return  carColorsService.findById(colorId);
    }


    @PostMapping("/createdColor")
    @Caching(
            put= {@CachePut(cacheNames = "ColorCache", key = "#colorDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllColorCache",allEntries = true)}
    )
    public Map<String,String> createNewColor(@RequestBody ColorDTO colorDTO){
            return carColorsService.add(colorDTO);
    }



    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "ColorCache", key = "#colorDTO.id")	},
            evict = {@CacheEvict(cacheNames = "AllColorCache",allEntries = true)}
    )
    public Map<String,String>  updateColor(@RequestBody ColorDTO colorDTO){
        return carColorsService.update(colorDTO) ;
    }



    @DeleteMapping("{colorId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ColorCache",key = "#id"),
                    @CacheEvict(cacheNames = "AllColorCache",allEntries = true)
            }
    )
    public Map<String,String> deleteColor(@PathVariable("colorId") int id){
        return  carColorsService.delete(id);
    }
}
