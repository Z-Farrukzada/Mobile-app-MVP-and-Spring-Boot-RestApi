package com.zaurfarrukhzada.carannouncementmobileproject.View.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.zaurfarrukhzada.carannouncementmobileproject.R;

public class SplashActivity extends AppCompatActivity implements ISplashContract.View {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter(this);
        presenter.Start();
    }
    @Override
    public void init() {
        this.presenter.loadingAnim(this);
    }
}