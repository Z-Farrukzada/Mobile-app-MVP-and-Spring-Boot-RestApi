package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.ForgotPassword;

import android.app.Activity;

public interface IForgotPasswordFragmentContract {
    
    interface View{

        void showLoadingDialog();

        void hideLoadingDialog();

        void success(String message);

        void failed(int message);

    }
    interface Presenter{

        void created();

        void changePassword(String email, String password, Activity activity);

        void success(String message);

        void failed(int message);
    }
    
}
