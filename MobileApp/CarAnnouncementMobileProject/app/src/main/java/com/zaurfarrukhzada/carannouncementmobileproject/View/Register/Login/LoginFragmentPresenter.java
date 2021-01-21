package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login.ILoginFragmentContract;

public class LoginFragmentPresenter implements  ILoginFragmentContract.Presenter {

    ILoginFragmentContract.View mView;

    public LoginFragmentPresenter(ILoginFragmentContract.View mView) {
        this.mView = mView;
    }
    @Override
    public void Start() {
        this.mView.init();
        this.mView.clickForgotPasswordLink();
        this.mView.clickedTransitionLogin();

    }
    @Override
    public void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment)  {
        FragmentTransaction fragmentTransaction =((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.slide_out,R.anim.fade_in);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}