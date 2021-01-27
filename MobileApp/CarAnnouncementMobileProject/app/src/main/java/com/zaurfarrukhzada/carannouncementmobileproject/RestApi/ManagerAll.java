package com.zaurfarrukhzada.carannouncementmobileproject.RestApi;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.User;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends  BaseManager{

    private final static ManagerAll ourInstance = new ManagerAll();

    private  ManagerAll(){

    }

    public static synchronized ManagerAll  getInstance(){
        return ourInstance;
    }

    public Call<List<City>> getInfoData(){
        return  getRestApiClient().getAllCity();
    }

    public  Call<Message> getCreateNewUser(User user){
        return  getRestApiClient().registerUser(user);
    }

    public Call<Long> getWithByNameId(String cityName){
        return  getRestApiClient().withNameFindId(cityName);
    }

    public Call<Message> getLoginUser(JsonObject jsonObject){
        return  getRestApiClient().loginUser(jsonObject);
    }

    public Call<Message> getChangeUserPassword(JsonObject jsonObject){
        return  getRestApiClient().changePassword(jsonObject);
    }

}
