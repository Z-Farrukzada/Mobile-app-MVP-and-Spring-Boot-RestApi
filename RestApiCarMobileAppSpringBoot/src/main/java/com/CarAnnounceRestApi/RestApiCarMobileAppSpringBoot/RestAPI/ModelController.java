package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ModelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ModelService;
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
@RequestMapping("/api/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    @SneakyThrows
    @GetMapping
    @Cacheable(cacheNames = "AllModelCache")
    public  List<ModelDTO> allListModel(){
        return  modelService.getAll();
    }

    @GetMapping("/{modelId}")
    @Cacheable(cacheNames = "ModelCache")
    public ModelDTO findByIdModel(@PathVariable("modelId") int modelId){
        return  modelService.findById(modelId);
    }

    @GetMapping("/select/{brandId}")
    @Cacheable(cacheNames = "ModelWithBrandCache")
    public List<ModelDTO> findByModelByBrandId(@PathVariable("brandId") int brandId){
        return modelService.FindByModelByBrandId(brandId);
    }

    @PostMapping("/created")
    @Caching(
            put= {@CachePut(cacheNames = "ModelCache", key = "#modelDTO.id")	,
                    @CachePut(cacheNames = "ModelWithBrandCache", key = "#modelDTO.id")},
            evict = {@CacheEvict(cacheNames = "AllModelCache",allEntries = true)}
    )
    public  Map<String,String> createdNewModel(@RequestBody ModelDTO modelDTO){
        return   modelService.add(modelDTO);
    }

     @PutMapping("/updated")
     @Caching(
             put= {@CachePut(cacheNames = "ModelCache", key = "#modelDTO.id")	,
                     @CachePut(cacheNames = "ModelWithBrandCache", key = "#modelDTO.id")},
             evict = {@CacheEvict(cacheNames = "AllModelCache",allEntries = true)}
     )
    public  Map<String,String> updateModel(@RequestBody ModelDTO modelDTO){
         return  modelService.update(modelDTO);
     }

     @DeleteMapping("/{modelId}")
     @Caching(
             evict = {
                     @CacheEvict(cacheNames = "ModelCache",key = "#modelId"),
                     @CacheEvict(cacheNames = "AllModelCache",allEntries = true)
             }
     )
    public Map<String,String> deleteModel(@PathVariable("modelId") int modelId){
        return  modelService.delete(modelId);
     }


}
