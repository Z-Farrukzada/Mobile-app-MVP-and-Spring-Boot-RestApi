package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ban")
public class CarBanController {

    @Autowired
    CarBanService carBanService;


    @GetMapping
    public ResponseEntity<List<CarBan>> allListCarBans(){
        return new ResponseEntity<>(carBanService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{banId}")
    public ResponseEntity<CarBan> findByIdCarBan(@PathVariable("banId") int id){
        return  new ResponseEntity<>( carBanService.findById(id),HttpStatus.OK);
    }
    @PostMapping("/createdBan")
    public ResponseEntity<Map<String,String>> createNewBan(@RequestBody CarBan carBan){
        carBanService.add(carBan);
        Map<String,String> map = new HashMap<>();
        map.put("New Ban","Created");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateColor(@RequestBody CarBan carBan){
        carBanService.update(carBan);
        Map<String,String> map = new HashMap<>();
        map.put("Ban","Updated");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("{banId}")
    public ResponseEntity<Map<String,String>> deleteColor(@PathVariable("banId") int id){
        carBanService.delete(id);
        Map<String,String> map = new HashMap<>();
        map.put("Ban","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }


}
