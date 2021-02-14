package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CityDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @SneakyThrows
    @GetMapping("/all")
    @Cacheable(cacheNames = "AllCityCache")
    public List<CityDTO> allListCity(){
        return  cityService.getAll();
    }

    @GetMapping("/{cityId}")
    @Cacheable(cacheNames = "CityCache")
    public CityDTO findByIdCity(@PathVariable("cityId") int cityId){
        return  cityService.findById(cityId);
    }

    @GetMapping("name/{cityName}")
    @Cacheable(cacheNames = "CityNameCache")
    public Long WithNameFindId(@PathVariable("cityName") String cityName){
       return  cityService.WithNameFindCityId(cityName);
    }

    @PostMapping("/createdCity")
    @Caching(
            put= {@CachePut(cacheNames = "CityCache", key = "#cityDTO.id"),
                    @CachePut(cacheNames ="CityNameCache" ,key = "#cityDTO.name")},
            evict = {@CacheEvict(cacheNames = "AllCityCache",allEntries = true)}
    )
    public Map<String,String> createNewCity(@RequestBody CityDTO cityDTO){
        return cityService.add(cityDTO);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "CityCache", key = "#cityDTO.id"),
                    @CachePut(cacheNames ="CityNameCache" ,key = "#cityDTO.name")},
            evict = {@CacheEvict(cacheNames = "AllCityCache",allEntries = true)}
    )
    public Map<String,String> updateCity(@RequestBody CityDTO cityDTO){
        return cityService.update(cityDTO);
    }

    @DeleteMapping("{cityId}")
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "CityCache",key = "#id"),
                    @CacheEvict(cacheNames = "AllCityCache",allEntries = true)
            }
    )
    public Map<String,String> deleteCity(@PathVariable("cityId") int id){
        return  cityService.delete(id);
    }


}
