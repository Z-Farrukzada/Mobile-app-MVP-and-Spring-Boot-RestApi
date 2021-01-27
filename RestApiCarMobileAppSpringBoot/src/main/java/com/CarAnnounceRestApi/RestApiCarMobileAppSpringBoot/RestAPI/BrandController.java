package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BrandServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    BrandServices brandServices;


    @GetMapping
    public ResponseEntity<List<CarBrand>> allListBrand(){
           return  new ResponseEntity<>(brandServices.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{brandId}")
    public ResponseEntity<CarBrand> findByIdBrand(@PathVariable("brandId") int brandId){
         return  new ResponseEntity<>(brandServices.findById(brandId),HttpStatus.OK);
    }
    @PostMapping("/createBrand")
    public ResponseEntity<Map<String,String>> addBrand(@RequestBody CarBrand carBrand){
           brandServices.add(carBrand);
           Map<String,String> map = new HashMap<>();
           map.put("Brand","Created");
           return  new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedBrand(@RequestBody CarBrand carBrand){
           brandServices.update(carBrand);
           Map<String,String> map = new HashMap<>();
           map.put("Brand","Updated");
           return  new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/{brandId}")
    public ResponseEntity<Map<String,String>> deletedBrand(@PathVariable("brandId") int brandId){
           brandServices.delete(brandId);
           Map<String,String> map = new HashMap<>();
           map.put("Brand","Deleted");
           return  new ResponseEntity<>(map,HttpStatus.OK);
    }

}
