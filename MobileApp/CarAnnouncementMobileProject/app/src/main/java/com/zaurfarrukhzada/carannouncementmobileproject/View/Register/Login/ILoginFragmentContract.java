package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public interface ILoginFragmentContract {

    interface  View {
        void init();
        void clickedTransitionLogin();
        void clickForgotPasswordLink();
        void loginUser();
    }
    interface  Presenter{
        void Start();
        void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);
        void checkInputLoginAccount();
        void loginUserIsAccount(EditText loginEmailInput,EditText loginPasswordInput,Button loginBtn,Activity context);
    }
}
