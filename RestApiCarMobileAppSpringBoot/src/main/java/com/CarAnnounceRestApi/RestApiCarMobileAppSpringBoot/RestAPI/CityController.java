package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.City;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarColorsService;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CityService;
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


    @GetMapping("/all")
    public ResponseEntity<List<City>> allListCity(){
        return  new ResponseEntity<>(cityService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{cityId}")
    public ResponseEntity<City> findByIdCity(@PathVariable("cityId") int cityId){
        return  new ResponseEntity<>(cityService.findById(cityId),HttpStatus.OK);
    }
    @GetMapping("name/{cityName}")
    public ResponseEntity<Long> WithNameFindId(@PathVariable("cityName") String cityName){
       return  new ResponseEntity<>(cityService.WithNameFindCityId(cityName),HttpStatus.OK);
    }

    @PostMapping("/createdCity")
    public ResponseEntity<Map<String,String>> createNewCity(@RequestBody City city){
        cityService.add(city);
        Map<String,String> map = new HashMap<>();
        map.put("New City","Created");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateCity(@RequestBody City city){
        cityService.update(city);
        Map<String,String> map = new HashMap<>();
        map.put("City","Updated");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("{cityId}")
    public ResponseEntity<Map<String,String>> deleteCity(@PathVariable("cityId") int id){
        cityService.delete(id);
        Map<String,String> map = new HashMap<>();
        map.put("City","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }


}
