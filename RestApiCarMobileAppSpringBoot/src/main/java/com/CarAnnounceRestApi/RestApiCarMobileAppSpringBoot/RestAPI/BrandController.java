package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BrandServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    BrandServices brandServices;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "AllBrandCache")
    public List<Map<String,Object>> allListBrand() throws IOException {
        return brandList(brandServices.getAll());
    }

    @GetMapping(value = "/popList",produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "AllBrandPopCache")
    public List<Map<String,Object>> popListBrand() throws IOException {
        return brandServices.popularBrand();
    }

    @GetMapping("/{brandId}")
    @Cacheable(cacheNames = "BrandCache")
    public BrandDTO findByIdBrand(@PathVariable("brandId") int brandId){
         return  brandServices.findById(brandId);
    }

    @PostMapping("/createBrand")
    @Caching(
            put= {@CachePut(cacheNames = "BrandCache", key = "#brandDto.id")	},
            evict = {@CacheEvict(cacheNames = "AllBrandCache",allEntries = true),
                    @CacheEvict(cacheNames = "AllBrandPopCache",allEntries = true)}
    )
    public Map<String,String> addBrand(@RequestBody BrandDTO brandDto){
        return  brandServices.add(brandDto);
    }

    @PutMapping("/updated")
    @Caching(
            put= {@CachePut(cacheNames = "BrandCache", key = "#brandDto.id")	},
            evict = {@CacheEvict(cacheNames = "AllBrandCache",allEntries = true),
                    @CacheEvict(cacheNames = "AllBrandPopCache",allEntries = true)}
    )
    public Map<String,String> updatedBrand(@RequestBody BrandDTO brandDTO){
        return  brandServices.update(brandDTO);
    }

    @DeleteMapping("/{brandId}")
    @Caching(
            evict = {@CacheEvict(cacheNames = "AllBrandCache",allEntries = true),
                    @CacheEvict(cacheNames = "AllBrandPopCache",allEntries = true)}
    )
    public Map<String,String> deletedBrand(@PathVariable("brandId") int brandId){
        return  brandServices.delete(brandId);
    }


    private List<Map<String,Object>> brandList(List<BrandDTO> popularBrand) throws IOException {
        List<Map<String,Object>> newdata = new ArrayList<>();
        for(BrandDTO carBrand:popularBrand){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carBrand.getId());
            map.put("name",carBrand.getName());
            map.put("logoImage", Convert.ConvertBase64(carBrand.getLogoImage()));
            newdata.add(map);
        }
        return newdata;
    }

}
