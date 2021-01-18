package com.zaurfarrukhzada.carannouncementmobileproject.Presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zaurfarrukhzada.carannouncementmobileproject.Contract.IRegisterContract;
import com.zaurfarrukhzada.carannouncementmobileproject.R;

import java.util.Objects;

import butterknife.BindView;

public class RegisterPresenter implements IRegisterContract.Presenter {

    private final IRegisterContract.View mView;

    public RegisterPresenter(IRegisterContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
        mView.DefaultFragment();

    }

    @Override
    public void setFragment(Fragment fragment,Activity activity,FrameLayout frameLayout) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}
