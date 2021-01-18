package com.zaurfarrukhzada.carannouncementmobileproject.Presenter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.zaurfarrukhzada.carannouncementmobileproject.Contract.ILoadingContract;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.MainActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.View.RegisterActivity;

public class LoadingPresenter implements ILoadingContract.Presenter {

    ILoadingContract.View mView;
    Thread timer;
    public LoadingPresenter(ILoadingContract.View mView) {
        this.mView = mView;
    }
    @Override
    public void Start() {
        mView.LoadingTransitionMain();
    }
    @Override
    public void StartLoading(Activity context) {
        timer = new Thread(){
            @Override
            public void run() {
                try { synchronized (this){ wait(5000); }
                }catch(InterruptedException e){ e.printStackTrace(); }finally {
                    Intent registerIntent = new Intent(context, RegisterActivity.class);
                    ActivityOptions options=ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                    context.startActivity(registerIntent,options.toBundle());
                    context.finish();
                }
            }
        };
        timer.start();
    }
}
