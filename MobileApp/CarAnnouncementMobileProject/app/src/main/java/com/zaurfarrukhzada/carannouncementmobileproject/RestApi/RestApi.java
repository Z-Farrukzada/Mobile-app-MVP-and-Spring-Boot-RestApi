package com.zaurfarrukhzada.carannouncementmobileproject.RestApi;

import com.zaurfarrukhzada.carannouncementmobileproject.Model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("cities/all")
    Call<List<City>> getAllCity(); /*Call All Cities*/


    @POST("user/register")
    Call<Message> registerUser(@Body User user); /*Create New User*/


    @GET("cities/name/{cityName}")
    Call<Long> withNameFindId(@Path("cityName") String cityName);//City name find Id;

}
