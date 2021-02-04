package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.BrandServices;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    BrandServices brandServices;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String,Object>>> allListBrand() throws IOException {
        List<CarBrand> carBrandList = brandServices.getAll();
        List<Map<String,Object>> newdata = new ArrayList<>();
        for(CarBrand carBrand:carBrandList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carBrand.getId());
            map.put("name",carBrand.getName());
            map.put("logoImage", Convert.ConvertBase64(carBrand.getLogoImage()));
            newdata.add(map);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newdata);
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
