package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;


import com.zaurfarrukhzada.carannouncementmobileproject.R;


public class SplashActivity extends AppCompatActivity  implements  ISplashActivityContract.View{


    SplashActivityPresenter splashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivityPresenter = new SplashActivityPresenter(this);
        this.splashActivityPresenter.created();
    }

    @Override
    public Activity callActivity() {
        return this;
    }

    @Override
    public void startAnimation() {
        this.splashActivityPresenter.start();
    }


}