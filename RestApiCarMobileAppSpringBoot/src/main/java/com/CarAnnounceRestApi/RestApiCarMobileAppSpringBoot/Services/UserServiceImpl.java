package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.User;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomAuthException;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String username, String email, String password, String phone, int cityId) throws CustomAuthException {
         Pattern pattern = Pattern.compile("^(.+)@(.+)$");

         if(!pattern.matcher(email).matches()){
              throw  new CustomAuthException("Invalid email format");
         }
         Long count = userRepository.getCountByEmail(email);
         if(count>0){
             throw new CustomAuthException("This email is already exists");
         }
         Integer userId =  userRepository.create(username,email,password,phone,cityId);

          return  userRepository.findById(userId);
    }

    @Override
    public User validate(String email, String password) throws CustomAuthException {
        return userRepository.findEmailAndPassword(email,password);
    }
}
