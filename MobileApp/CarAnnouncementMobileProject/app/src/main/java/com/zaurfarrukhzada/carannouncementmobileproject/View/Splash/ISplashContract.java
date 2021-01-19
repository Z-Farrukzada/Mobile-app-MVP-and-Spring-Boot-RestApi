package com.zaurfarrukhzada.carannouncementmobileproject.View.Splash;

import android.app.Activity;


public interface ISplashContract {
    
    interface  View{
        void init();
    }
    interface  Presenter{

        void Start();

        void loadingAnim(Activity activity);
    }
}
