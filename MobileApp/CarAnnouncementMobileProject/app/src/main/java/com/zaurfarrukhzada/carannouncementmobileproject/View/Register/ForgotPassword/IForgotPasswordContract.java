package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.ForgotPassword;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.JsonObject;

public interface IForgotPasswordContract {
    interface  View{

        void init();
        void goBackLoginPage();
        void checkEmail();

    }
    interface  Presenter{

        void Start();
        void setLoginFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);
        void checkForgotEmailInput();
        void sendEmailAndChangePassword(EditText forgotEmailInput, Button forgotBtn,
                                        Activity activity, android.view.View view);
    }
}
