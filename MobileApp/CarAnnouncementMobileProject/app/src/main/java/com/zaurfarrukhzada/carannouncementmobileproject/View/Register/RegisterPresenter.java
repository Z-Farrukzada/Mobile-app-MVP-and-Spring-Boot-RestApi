package com.zaurfarrukhzada.carannouncementmobileproject.View.Register;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class RegisterPresenter  implements  IRegisterContract.Presenter{

    IRegisterContract.View mView;

    public RegisterPresenter(IRegisterContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
        mView.bindView();
        mView.defaultPage();
        mView.isDefault();
    }

    @Override
    public void defaultFragment(Activity activity, FrameLayout frameLayout, Fragment fragment) {
        FragmentTransaction fragmentTransaction =((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}
