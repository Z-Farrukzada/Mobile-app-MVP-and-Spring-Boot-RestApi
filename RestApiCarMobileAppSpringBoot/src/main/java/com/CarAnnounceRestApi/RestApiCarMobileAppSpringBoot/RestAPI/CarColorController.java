package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarColorsService;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<ColorDTO>> allListColors(){
         return  new ResponseEntity<>(carColorsService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<ColorDTO> findByIdColor(@PathVariable("colorId") int colorId){
        return  new ResponseEntity<>(carColorsService.findById(colorId),HttpStatus.OK);
    }

    @PostMapping("/createdColor")
    public ResponseEntity<Map<String,String>> createNewColor(@RequestBody ColorDTO colorDTO){
            return new ResponseEntity<>(carColorsService.add(colorDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateColor(@RequestBody ColorDTO colorDTO){
        return new ResponseEntity<>(carColorsService.update(colorDTO),HttpStatus.OK);
    }

    @DeleteMapping("{colorId}")
    public ResponseEntity<Map<String,String>> deleteColor(@PathVariable("colorId") int id){
        return  new ResponseEntity<>(carColorsService.delete(id),HttpStatus.OK);
    }
}
