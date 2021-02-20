package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;


import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;


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

        void onGetDatSlider(List<SliderItem> sliderItems);

        void transitionAllBrandActivity();

        void bottomNavConfig();

        void slideConfig();
    }

    interface Presenter{

        void created();

        void callAll();

        void success(List<CarBrand> body);

        void successSlide(List<SliderItem> body);
    }

}
