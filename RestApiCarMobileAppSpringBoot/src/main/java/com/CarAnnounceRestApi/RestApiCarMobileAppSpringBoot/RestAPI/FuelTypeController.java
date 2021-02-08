package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarFuelType;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.DetailService;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.FuelTypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fuels")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<CarFuelType>> allListCarFuels(){
        return new ResponseEntity<>(fuelTypeService.getAll(), HttpStatus.OK);
    }
    @GetMapping("{fuelId}")
    public ResponseEntity<CarFuelType> findByIdFuel(@PathVariable("fuelId") int fuelId){
        return  new ResponseEntity<>(fuelTypeService.findById(fuelId),HttpStatus.OK);
    }
    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewFuel(@RequestBody CarFuelType carFuelType){
        fuelTypeService.add(carFuelType);
        Map<String,String> map = new HashMap<>();
        map.put("New Fuel","Created");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedFuel(@RequestBody CarFuelType carFuelType){
        fuelTypeService.update(carFuelType);
        Map<String,String> map = new HashMap<>();
        map.put("Fuel","Updated");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/{fuelId}")
    public ResponseEntity<Map<String,String>> deletedDetail(@PathVariable("fuelId") int fuelId){
        fuelTypeService.delete(fuelId);
        Map<String,String> map = new HashMap<>();
        map.put("Fuel","Deleted");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
