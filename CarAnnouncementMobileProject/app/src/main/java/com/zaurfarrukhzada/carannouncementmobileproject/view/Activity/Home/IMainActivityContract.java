package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;

import java.util.List;

public interface IMainActivityContract {

    interface View{

        void init();

        void setDrawer();

        void brandRecycleConfig();

        void onGetDataSuccess(List<CarBrand> carBrandList);

        void getAllBrands();

        void showDialogLoading();

        void hideDialogLoading();

        void transitionAllBrandActivity();

        void bottomNavConfig();
    }

    interface Presenter{

        void created();

        void callAllBrands();

        void success(List<CarBrand> body);
    }

}
