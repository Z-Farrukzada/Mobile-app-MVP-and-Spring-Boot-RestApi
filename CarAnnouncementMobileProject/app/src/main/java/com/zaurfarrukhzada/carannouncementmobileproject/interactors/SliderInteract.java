package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.util.Log;

import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.IMainActivityContract;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderInteract {

    IMainActivityContract.Presenter mPresenter;

    public SliderInteract(IMainActivityContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void getAllSliders() {
        Call<List<SliderItem>> call = ManagerAll.getInstance().getCallSlider();
        call.enqueue(new Callback<List<SliderItem>>() {
            @Override
            public void onResponse(@NotNull Call<List<SliderItem>> call, @NotNull Response<List<SliderItem>> response) {
               mPresenter.successSlide(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<SliderItem>> call, @NotNull Throwable t) {

            }
        });
    }
}
