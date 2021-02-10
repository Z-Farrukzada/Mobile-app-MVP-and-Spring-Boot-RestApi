package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.DetailDTO;
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
    public ResponseEntity<List<DetailDTO>> allListCarDetail(){
        return new ResponseEntity<>(detailService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{detailId}")
    public ResponseEntity<DetailDTO> findByIdDetail(@PathVariable("detailId") int detailId){
        return  new ResponseEntity<>(detailService.findById(detailId),HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Map<String,String>> createdNewDetails(@RequestBody DetailDTO detailDTO){
        return new ResponseEntity<>(detailService.add(detailDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>> updatedDetail(@RequestBody DetailDTO detailDTO){
        return  new ResponseEntity<>(detailService.update(detailDTO),HttpStatus.OK);
    }

    @DeleteMapping("{detailId}")
    public ResponseEntity<Map<String,String>> deletedDetail(@PathVariable("detailId") int detailId){
        return new ResponseEntity<>(detailService.delete(detailId),HttpStatus.OK);
    }
}
