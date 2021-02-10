package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.FuelDTO;
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
    public ResponseEntity<List<FuelDTO>> allListCarFuels(){
        return new ResponseEntity<>(fuelTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{fuelId}")
    public ResponseEntity<FuelDTO> findByIdFuel(@PathVariable("fuelId") int fuelId){
        return  new ResponseEntity<>(fuelTypeService.findById(fuelId),HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewFuel(@RequestBody FuelDTO fuelDTO){
        return new ResponseEntity<>(fuelTypeService.add(fuelDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedFuel(@RequestBody FuelDTO fuelDTO){
        return  new ResponseEntity<>( fuelTypeService.update(fuelDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{fuelId}")
    public ResponseEntity<Map<String,String>> deletedDetail(@PathVariable("fuelId") int fuelId){
        return new ResponseEntity<>(fuelTypeService.delete(fuelId),HttpStatus.OK);
    }

}
