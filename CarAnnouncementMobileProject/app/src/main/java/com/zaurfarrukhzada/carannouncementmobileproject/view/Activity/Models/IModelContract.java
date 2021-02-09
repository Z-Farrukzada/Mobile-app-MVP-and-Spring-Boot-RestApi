package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;

import java.util.List;

public interface IModelContract {
    
    interface View{
        
        void showLoadingDialog();
        
        void hideLoadingDialog();

        void init();

        void layoutInit();

        void supportToolbar();

        void success(List<CarModel> body);
    }
    
    interface Presenter{
        
        void created();

        void getFindModel(int id, Activity activity);

        void success(List<CarModel> body);
    }
    
}
