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
       return new ResponseEntity<>(userService.register(userMap),HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> userMap){
        return new ResponseEntity<>(userService.validate(userMap),HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Map<String,String>> changePassword(@RequestBody Map<String,String> userMap)  {
        return  new ResponseEntity<>(userService.findEmailChangePassword(userMap),HttpStatus.OK);

    }
    
}
