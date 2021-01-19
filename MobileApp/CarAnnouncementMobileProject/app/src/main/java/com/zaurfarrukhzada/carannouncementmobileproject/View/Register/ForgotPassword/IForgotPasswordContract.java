package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.ForgotPassword;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public interface IForgotPasswordContract {
    interface  View{


        void init();

        void goBackLoginPage();
    }
    interface  Presenter{


        void Start();

        void setLoginFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);
    }
}
