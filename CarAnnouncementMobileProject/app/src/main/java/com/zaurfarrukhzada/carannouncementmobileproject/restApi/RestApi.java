package com.zaurfarrukhzada.carannouncementmobileproject.restApi;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Announcement;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;
import com.zaurfarrukhzada.carannouncementmobileproject.model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("cities/all")
    Call<List<City>> getAllCity(); /*Call All Cities*/

    @POST("user/register")
    Call<Message> registerUser(@Body User user); /*Create New User*/

    @POST("user/login")
    Call<Message> loginUser(@Body JsonObject jsonObject); //Login user

    @POST("user/changePassword")
    Call<Message> changePassword(@Body JsonObject jsonObject);  //ChangeUserPasswordWithEmail

    @GET("brands/popList")
    Call<List<CarBrand>> getCallPopBrands();   //Call Pop Brands

    @GET("brands")
    Call<List<AllCarBrand>> getCallAllBrands();   //Call All Brands

    @GET("models/select/{brandId}")
    Call<List<CarModel>> getFindByIdModels(@Path("brandId") int id); //Find Car Models by brand id;

    @GET("slide")
    Call<List<SliderItem>> getCallSliderImage(); //Call Main Sliders

    @GET("announcement")
    Call<List<Announcement>> getCallPopCarAnnouncement();//Call Populyar Announcement

}
