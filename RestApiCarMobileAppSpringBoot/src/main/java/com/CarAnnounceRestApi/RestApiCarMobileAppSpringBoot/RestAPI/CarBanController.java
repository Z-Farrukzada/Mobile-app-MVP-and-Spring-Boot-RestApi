package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.BanDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.CarBanService;
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
@RequestMapping("api/ban")
public class CarBanController {

    @Autowired
    CarBanService carBanService;


    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<BanDTO>> allListCarBans(){
        return new ResponseEntity<>(carBanService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{banId}")
    public ResponseEntity<BanDTO> findByIdCarBan(@PathVariable("banId") int id){
        return  new ResponseEntity<>( carBanService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/createdBan")
    public ResponseEntity<Map<String,String>> createNewBan(@RequestBody BanDTO banDTO){
        return new ResponseEntity<>(carBanService.add(banDTO),HttpStatus.CREATED);
    }

    @PutMapping("/updated")
    public ResponseEntity<Map<String,String>>  updateColor(@RequestBody BanDTO banDTO){
        return new ResponseEntity<>( carBanService.update(banDTO),HttpStatus.OK);
    }

    @DeleteMapping("{banId}")
    public ResponseEntity<Map<String,String>> deleteColor(@PathVariable("banId") int id){
        return  new ResponseEntity<>(carBanService.delete(id),HttpStatus.OK);
    }


}
