package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.ModelInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;

import java.util.List;

public class ModelPresenter implements IModelContract.Presenter{

    IModelContract.View mView;
    ModelInteract modelInteract;

    public ModelPresenter(IModelContract.View mView) {
        this.mView = mView;
        modelInteract = new ModelInteract(this);
    }

    @Override
    public void created() {
        this.mView.init();
        this.mView.layoutInit();
        this.mView.supportToolbar();
    }

    @Override
    public void getFindModel(int id,Activity activity) {
        this.mView.showLoadingDialog();
        this.modelInteract.findModelWithBrandId(id,activity);
    }

    @Override
    public void success(List<CarModel> body) {
        this.mView.hideLoadingDialog();
        this.mView.success(body);
    }
}
