package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login;

import android.app.Activity;

public interface ILoginFragmentContract {

    interface View{

        void Success(String message);

        void Failed(int message);

        void showLoadingDialog();

        void hideLoadingDialog();

        void checkSharedPreferences();
    }
    interface  Presenter{

        void created();

        void login(String email, String password, Activity activity);

        void Success(String message);

        void Failed(int message);
    }

}
