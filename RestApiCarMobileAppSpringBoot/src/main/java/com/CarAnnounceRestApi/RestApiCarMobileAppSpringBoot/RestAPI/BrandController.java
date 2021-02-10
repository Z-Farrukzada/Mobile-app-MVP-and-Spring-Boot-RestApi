package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64.Convert;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BrandDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrand;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBrandWithModelCount;
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
        return brandList(brandServices.getAll());
    }

    @GetMapping(value = "/popList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String,Object>>> popListBrand() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(brandServices.popularBrand());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDTO> findByIdBrand(@PathVariable("brandId") int brandId){
         return  new ResponseEntity<>(brandServices.findById(brandId),HttpStatus.OK);
    }

    @PostMapping("/createBrand")
    public ResponseEntity<Map<String,String>> addBrand(@RequestBody BrandDTO brandDto){
        return  new ResponseEntity<>(brandServices.add(brandDto),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedBrand(@RequestBody BrandDTO brandDTO){
        return  new ResponseEntity<>(brandServices.update(brandDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Map<String,String>> deletedBrand(@PathVariable("brandId") int brandId){
        return  new ResponseEntity<>(brandServices.delete(brandId),HttpStatus.OK);
    }


    private ResponseEntity<List<Map<String,Object>>> brandList(List<BrandDTO> popularBrand) throws IOException {
        List<Map<String,Object>> newdata = new ArrayList<>();
        for(BrandDTO carBrand:popularBrand){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carBrand.getId());
            map.put("name",carBrand.getName());
            map.put("logoImage", Convert.ConvertBase64(carBrand.getLogoImage()));
            newdata.add(map);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newdata);
    }

}
