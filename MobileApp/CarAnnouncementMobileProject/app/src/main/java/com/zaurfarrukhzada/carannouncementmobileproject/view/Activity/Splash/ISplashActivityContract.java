package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Splash;


import android.app.Activity;

public interface ISplashActivityContract {

    interface  View{

        void startAnimation();

        Activity callActivity();
    }

    interface  Presenter{

        void start();

        void created();
    }

}
