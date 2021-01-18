package com.zaurfarrukhzada.carannouncementmobileproject.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zaurfarrukhzada.carannouncementmobileproject.Contract.ILoadingContract;
import com.zaurfarrukhzada.carannouncementmobileproject.Presenter.LoadingPresenter;
import com.zaurfarrukhzada.carannouncementmobileproject.R;

public class SplashActivity extends AppCompatActivity implements ILoadingContract.View {

    private LoadingPresenter loadingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadingPresenter = new LoadingPresenter(SplashActivity.this);
        loadingPresenter.Start();

    }
    @Override
    public void LoadingTransitionMain() {
       loadingPresenter.StartLoading(SplashActivity.this);
    }
}