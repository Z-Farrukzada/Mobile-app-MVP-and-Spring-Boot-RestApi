package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands;

import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;

import java.util.List;

public interface IAllBrandsContract {

    interface View{

        void initalize();

        void showDialogLoading();

        void hideDialogLoading();

        void callAllBrands();

        void getDataSuccess(List<AllCarBrand> body);

        void recycleConfig();
    }

    interface Presenter{

        void created();

        void getAllBrands();

        void success(List<AllCarBrand> body);
    }

}
