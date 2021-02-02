package com.zaurfarrukhzada.carannouncementmobileproject.restApi;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {

    @GET("cities/all")
    Call<List<City>> getAllCity(); /*Call All Cities*/

    @POST("user/register")
    Call<Message> registerUser(@Body User user); /*Create New User*/

    @POST("user/login")
    Call<Message> loginUser(@Body JsonObject jsonObject); //Login user

    @POST("user/changePassword")
    Call<Message> changePassword(@Body JsonObject jsonObject);  //ChangeUserPasswordWithEmail

}
