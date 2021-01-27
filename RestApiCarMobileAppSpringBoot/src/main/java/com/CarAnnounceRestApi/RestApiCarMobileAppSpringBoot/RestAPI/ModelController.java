package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ModelService;
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


    @GetMapping
    public ResponseEntity<List<CarModel>>allListModel(){
        return  new ResponseEntity<>(modelService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{modelId}")
    public ResponseEntity<CarModel> findByIdModel(@PathVariable("modelId") int modelId){
        return new ResponseEntity<>(modelService.findById(modelId),HttpStatus.OK);
    }
    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewModel(@RequestBody CarModel carModel){
        Map<String,String> map = new HashMap<>();
        modelService.add(carModel);
        map.put("New Model","Created");
        return  new ResponseEntity<>(map,HttpStatus.CREATED);
    }
     @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updateModel(@RequestBody CarModel carModel){
         Map<String,String> map = new HashMap<>();
         modelService.update(carModel);
         map.put("Model","Updated");
         return new ResponseEntity<>(map,HttpStatus.OK);
     }
     @DeleteMapping("/{modelId}")
    public ResponseEntity<Map<String,String>> deleteModel(@PathVariable("modelId") int modelId){
        Map<String,String> map = new HashMap<>();
        modelService.delete(modelId);
        map.put("Model","Deleted");
        return  new ResponseEntity<>(map,HttpStatus.OK);
     }

}
