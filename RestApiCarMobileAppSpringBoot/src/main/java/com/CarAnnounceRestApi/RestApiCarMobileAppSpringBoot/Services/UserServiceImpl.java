package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomAuthException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public  Map<String,String> register(Map<String, String> userMap) {
        Map<String,String> map = new HashMap<>();
        try {
            String username = userMap.get("username");
            String password = userMap.get("password");
            String email = userMap.get("email");
            String phone = userMap.get("phone");
            int cityId = Integer.parseInt(userMap.get("city_id"));
            map = checkData(username,password,email,phone,cityId);
        }catch (Exception e){
            map.put("message",e.getMessage().toString());
        }
        return  map;
    }

    @Override
    public Map<String,String> validate(Map<String,String> userMap) {
        Map<String,String> map = new HashMap<>();
        try {
            String email = userMap.get("email");
            String password =userMap.get("password");
            User user = userRepository.findEmailAndPassword(email,password);
            if(!user.getPassword().equals(password)){
                map.put("message","Sifre ve email sehvdir");
            }else{
                map.put("message", user.getUsername().toUpperCase() + " xoş gəldin.");
            }
        }catch (Exception e){
            map.put("message","Zəhmət olmasa bir daha daxil edin.");
        }
        return map;
    }

    @Override
    public Map<String,String> findEmailChangePassword(Map<String,String> userMap) {
        Map<String,String> map = new HashMap<>();
        String email = userMap.get("email");
        String password = userMap.get("password");
        int data = userRepository.findEmailAndChangePassword(email,password);
        if (data > 0) {
            map.put("message","Şifrə dəyişdirildi.");
        } else {
            map.put("message","Səhv email");
        }
        return map;
    }

    private Map<String,String> checkData(String username, String password, String email, String phone, int cityId) {
        Map<String,String> map = new HashMap<>();
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(!pattern.matcher(email).matches()){
            map.put("message","Duzgun email formatinda daxil edin");
        }
        else{
            Long count = userRepository.getCountByEmail(email);
            if(count>0){
                map.put("message","Bu email artiq movcuddur");
            }else{
                Integer userId =  userRepository.create(username,email,password,phone,cityId);
                User user = userRepository.findById(userId);
                if(user==null){
                    map.put("message","Təşekkurlər Qeydiyyatdan kecdiniz.");
                }else{
                    map.put("message","Duzgun daxil edin");
                }
            }
        }
        return map;
    }
}
