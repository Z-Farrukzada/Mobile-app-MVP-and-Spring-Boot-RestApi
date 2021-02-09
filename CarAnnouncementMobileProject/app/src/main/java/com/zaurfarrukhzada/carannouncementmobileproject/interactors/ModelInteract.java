package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models.IModelContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelInteract {

    IModelContract.Presenter mPresenter;

    public ModelInteract(IModelContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void findModelWithBrandId(int id, Activity activity) {
        Call<List<CarModel>> listCall = ManagerAll.getInstance().getFindModelsWithByBrandId(id);
        listCall.enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CarModel>> call, @NotNull Response<List<CarModel>> response) {
                mPresenter.success(response.body());
            }
            @Override
            public void onFailure(@NotNull Call<List<CarModel>> call, @NotNull Throwable t) {
                Log.i(String.valueOf(activity),t.getLocalizedMessage());
            }
        });
    }
}
