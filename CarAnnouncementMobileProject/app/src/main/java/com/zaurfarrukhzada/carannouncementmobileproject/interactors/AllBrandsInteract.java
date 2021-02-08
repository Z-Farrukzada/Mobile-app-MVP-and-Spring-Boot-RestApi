package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.util.Log;

import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands.IAllBrandsContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBrandsInteract {

    IAllBrandsContract.Presenter mPresenter;

    public AllBrandsInteract(IAllBrandsContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void callAllBrandList() {

        Call<List<AllCarBrand>> callBrand = ManagerAll.getInstance().getCallBrands();
        callBrand.enqueue(new Callback<List<AllCarBrand>>() {
            @Override
            public void onResponse(@NotNull Call<List<AllCarBrand>> call, @NotNull Response<List<AllCarBrand>> response) {
                mPresenter.success(response.body());
                Log.i("data",response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<List<AllCarBrand>> call, @NotNull Throwable t) {
                Log.i("Brand",t.getLocalizedMessage());
            }
        });

    }
}
