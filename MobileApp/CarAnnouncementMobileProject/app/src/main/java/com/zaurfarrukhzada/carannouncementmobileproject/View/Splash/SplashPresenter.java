package com.zaurfarrukhzada.carannouncementmobileproject.View.Splash;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.RegisterActivity;

public class SplashPresenter implements ISplashContract.Presenter {

    ISplashContract.View mView;

    public SplashPresenter(ISplashContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
       mView.init();
    }

    @Override
    public void loadingAnim(Activity activity) {
     Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(7000);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent registerIntent = new Intent(activity,RegisterActivity.class);
                    ActivityOptions options =
                            ActivityOptions.makeCustomAnimation(activity, R.anim.fade_in, R.anim.fade_out);
                    activity.startActivity(registerIntent, options.toBundle());
                    activity.finish();
                }
            }
        };
        timer.start();
    }


}
