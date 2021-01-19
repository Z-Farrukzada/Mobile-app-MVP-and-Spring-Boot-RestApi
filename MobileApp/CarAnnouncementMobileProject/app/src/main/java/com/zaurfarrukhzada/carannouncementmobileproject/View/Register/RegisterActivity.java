package com.zaurfarrukhzada.carannouncementmobileproject.View.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount.CreateAccountFragment;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login.LoginFragment;


public class RegisterActivity extends AppCompatActivity  implements IRegisterContract.View {

    private  RegisterPresenter presenter;
    private FrameLayout frameLayout;
    private static  boolean defaultFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter(this);
        presenter.Start();
    }
    @Override
    public void bindView() {
       frameLayout = findViewById(R.id.register_main_frame);
    }

    @Override
    public void defaultPage() {
        this.presenter.defaultFragment(this,frameLayout,new CreateAccountFragment());
    }

    @Override
    public void isDefault() {
        if(defaultFragment){
            defaultFragment = false;
            this.presenter.defaultFragment(this,frameLayout,new CreateAccountFragment());
        }else{
            this.presenter.defaultFragment(this,frameLayout,new LoginFragment());
        }
    }

}