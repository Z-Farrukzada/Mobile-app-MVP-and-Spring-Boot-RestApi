package com.zaurfarrukhzada.carannouncementmobileproject.restApi;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;
import com.zaurfarrukhzada.carannouncementmobileproject.model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;

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

    public Call<Message> getLoginUser(JsonObject jsonObject){
        return  getRestApiClient().loginUser(jsonObject);
    }

    public Call<Message> getChangeUserPassword(JsonObject jsonObject){
        return  getRestApiClient().changePassword(jsonObject);
    }

    public Call<List<CarBrand>> getCallPopCarBrands(){
        return getRestApiClient().getCallPopBrands();
    }

    public Call<List<AllCarBrand>> getCallBrands(){
        return getRestApiClient().getCallAllBrands();
    }
    public Call<List<CarModel>> getFindModelsWithByBrandId(int id){
        return  getRestApiClient().getFindByIdModels(id);
    }

}
