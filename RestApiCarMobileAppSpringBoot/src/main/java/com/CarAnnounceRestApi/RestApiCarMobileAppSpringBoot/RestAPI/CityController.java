package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.CityDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarColorsService;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @SneakyThrows
    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> allListCity(){
        return  new ResponseEntity<>(cityService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> findByIdCity(@PathVariable("cityId") int cityId){
        return  new ResponseEntity<>(cityService.findById(cityId),HttpStatus.OK);
    }

    @GetMapping("name/{cityName}")
    public ResponseEntity<Long> WithNameFindId(@PathVariable("cityName") String cityName){
       return  new ResponseEntity<>(cityService.WithNameFindCityId(cityName),HttpStatus.OK);
    }

    @PostMapping("/createdCity")
    public ResponseEntity<Map<String,String>> createNewCity(@RequestBody CityDTO cityDTO){
        return new ResponseEntity<>(cityService.add(cityDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateCity(@RequestBody CityDTO cityDTO){
        return new ResponseEntity<>(cityService.update(cityDTO),HttpStatus.OK);
    }

    @DeleteMapping("{cityId}")
    public ResponseEntity<Map<String,String>> deleteCity(@PathVariable("cityId") int id){
        return  new ResponseEntity<>(cityService.delete(id),HttpStatus.OK);
    }


}
