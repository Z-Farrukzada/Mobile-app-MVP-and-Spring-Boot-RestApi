package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarDetail;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.DetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/details")
public class DetailController {

    @Autowired
    DetailService detailService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<CarDetail>> allListCarDetail(){
        return new ResponseEntity<>(detailService.getAll(),HttpStatus.OK);
    }
    @GetMapping("{detailId}")
    public ResponseEntity<CarDetail> findByIdDetail(@PathVariable("detailId") int detailId){
        return  new ResponseEntity<>(detailService.findById(detailId),HttpStatus.OK);
    }
    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewDetails(@RequestBody CarDetail carDetail){
        detailService.add(carDetail);
        Map<String,String> map = new HashMap<>();
        map.put("New Detail","Created");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedDetail(@RequestBody CarDetail carDetail){
        detailService.update(carDetail);
        Map<String,String> map = new HashMap<>();
        map.put("Detail","Updated");
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("{detailId}")
    public ResponseEntity<Map<String,String>> deletedDetail(@PathVariable("detailId") int detailId){
        detailService.delete(detailId);
        Map<String,String> map = new HashMap<>();
        map.put("Detail","Deleted");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
