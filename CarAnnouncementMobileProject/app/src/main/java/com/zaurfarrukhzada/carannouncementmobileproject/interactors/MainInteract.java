package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.IMainActivityContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteract {

    IMainActivityContract.Presenter presenter;

    public MainInteract(IMainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getCallAllBrands() {

        Call<List<CarBrand>> callBrand = ManagerAll.getInstance().getCallPopCarBrands();
        callBrand.enqueue(new Callback<List<CarBrand>>() {
            @Override
            public void onResponse(@NotNull Call<List<CarBrand>> call, @NotNull Response<List<CarBrand>> response) {
                presenter.success(response.body());
                Log.i("data",response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<List<CarBrand>> call, @NotNull Throwable t) {
                Log.i("Brand",t.getLocalizedMessage());
            }
        });
    }
}
