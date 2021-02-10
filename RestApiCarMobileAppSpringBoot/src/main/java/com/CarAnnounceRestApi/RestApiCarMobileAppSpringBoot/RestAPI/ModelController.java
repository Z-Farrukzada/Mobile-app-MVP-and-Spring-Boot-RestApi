package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ModelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ModelService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<ModelDTO>>allListModel(){
        return  new ResponseEntity<>(modelService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDTO> findByIdModel(@PathVariable("modelId") int modelId){
        return new ResponseEntity<>(modelService.findById(modelId),HttpStatus.OK);
    }

    @GetMapping("/select/{brandId}")
    public ResponseEntity<List<ModelDTO>> findBymModelByBrandId(@PathVariable("brandId") int brandId){
        return new ResponseEntity<>(modelService.FindByModelByBrandId(brandId),HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewModel(@RequestBody ModelDTO modelDTO){
        return  new ResponseEntity<>(modelService.add(modelDTO),HttpStatus.CREATED);
    }

     @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updateModel(@RequestBody ModelDTO modelDTO){
         return new ResponseEntity<>(modelService.update(modelDTO),HttpStatus.OK);
     }

     @DeleteMapping("/{modelId}")
    public ResponseEntity<Map<String,String>> deleteModel(@PathVariable("modelId") int modelId){
        return  new ResponseEntity<>(modelService.delete(modelId),HttpStatus.OK);
     }


}
