package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.MainInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;

import java.util.List;

public class MainActivityPresenter  implements IMainActivityContract.Presenter{

    IMainActivityContract.View mView;
    MainInteract mainInteract;

    public MainActivityPresenter(IMainActivityContract.View mView) {
        this.mView = mView;
        this.mainInteract = new MainInteract(this);
    }

    @Override
    public void created() {
        this.mView.init();
        this.mView.setDrawer();
        this.mView.brandRecycleConfig();
        this.mView.bottomNavConfig();
        this.mView.getAllBrands();
    }

    @Override
    public void callAllBrands() {
        this.mView.showDialogLoading();
        this.mainInteract.getCallAllBrands();
    }

    @Override
    public void success(List<CarBrand> body) {
        this.mView.hideDialogLoading();
        this.mView.onGetDataSuccess(body);
    }
}
