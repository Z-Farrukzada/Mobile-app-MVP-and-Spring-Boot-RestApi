package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarColorsService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public ResponseEntity<List<CarColors>> allListColors(){
         return  new ResponseEntity<>(carColorsService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{colorId}")
    public ResponseEntity<CarColors> findByIdColor(@PathVariable("colorId") int colorId){
        return  new ResponseEntity<>(carColorsService.findById(colorId),HttpStatus.OK);
    }
    @PostMapping("/createdColor")
    public ResponseEntity<Map<String,String>> createNewColor(@RequestBody CarColors carColors){
            carColorsService.add(carColors);
            Map<String,String> map = new HashMap<>();
            map.put("New Color","Created");
            return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateColor(@RequestBody CarColors carColors){
        carColorsService.update(carColors);
        Map<String,String> map = new HashMap<>();
        map.put("Colors","Updated");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("{colorId}")
    public ResponseEntity<Map<String,String>> deleteColor(@PathVariable("colorId") int id){
        carColorsService.delete(id);
        Map<String,String> map = new HashMap<>();
        map.put("Colors","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }
}
