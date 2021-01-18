package com.zaurfarrukhzada.carannouncementmobileproject.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.zaurfarrukhzada.carannouncementmobileproject.Contract.IRegisterContract;
import com.zaurfarrukhzada.carannouncementmobileproject.Presenter.RegisterPresenter;
import com.zaurfarrukhzada.carannouncementmobileproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterContract.View {


    private RegisterPresenter registerPresenter;
    @BindView(R.id.register_main_frame)  FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(RegisterActivity.this);
        registerPresenter.Start();
    }

    @Override
    public void DefaultFragment() {
         registerPresenter.setFragment(new CreateAccountFragment(),RegisterActivity.this,frameLayout);
    }

}