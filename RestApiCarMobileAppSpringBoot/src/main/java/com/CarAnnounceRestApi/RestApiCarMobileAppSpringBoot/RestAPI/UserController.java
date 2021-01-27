package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.RestAPI;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarBan;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.UserService;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
     UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Map<String, String> userMap){
       String username = userMap.get("username");
       String password = userMap.get("password");
       String email = userMap.get("email");
       String phone = userMap.get("phone");
       int cityId = Integer.parseInt(userMap.get("city_id"));
       User user = userService.register(username,email,password,phone,cityId);
       Map<String,String> map = new HashMap<>();
       map.put("message","Təşekkurlər Qeydiyyatdan kecdiniz.");
       return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> userMap){
        String email = userMap.get("email");
        String password =userMap.get("password");
        User user  = userService.validate(email,password);
        Map<String,String> map = new HashMap<>();
        map.put("message", user.getUsername().toUpperCase() + " xoş gəldin.");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Map<String,String>> changePassword(@RequestBody Map<String,String> userMap)  {
        String email = userMap.get("email");
        String password = userMap.get("password");
        String message = userService.findEmailChangePassword(email,password);
        Map<String,String> map = new HashMap<>();
        map.put("message",message);
        return  new ResponseEntity<>(map,HttpStatus.OK);

    }
    
}
