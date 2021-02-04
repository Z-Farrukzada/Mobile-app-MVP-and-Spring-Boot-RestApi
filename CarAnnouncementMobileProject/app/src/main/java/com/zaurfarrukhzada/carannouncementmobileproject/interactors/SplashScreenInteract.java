package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity;

public class SplashScreenInteract {


    public void SplashScreenAnimation(Activity activity) {
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent registerIntent = new Intent(activity, RegisterActivity.class);
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
