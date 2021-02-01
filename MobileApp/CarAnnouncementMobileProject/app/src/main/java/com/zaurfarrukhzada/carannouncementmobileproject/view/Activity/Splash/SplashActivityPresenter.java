package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Splash;


import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.SplashScreenInteract;

public class SplashActivityPresenter implements  ISplashActivityContract.Presenter{


    ISplashActivityContract.View mView;
    SplashScreenInteract splashScreenInteract;

    public SplashActivityPresenter(ISplashActivityContract.View mView) {
        this.mView = mView;
        this.splashScreenInteract = new SplashScreenInteract();
    }

    @Override
    public void created() {
        this.mView.startAnimation();
    }

    @Override
    public void start() {
        Activity activity = this.mView.callActivity();
        this.splashScreenInteract.SplashScreenAnimation(activity);
    }




}
