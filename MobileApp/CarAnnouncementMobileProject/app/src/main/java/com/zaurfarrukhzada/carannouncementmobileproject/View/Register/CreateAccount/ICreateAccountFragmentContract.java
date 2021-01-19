package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public interface ICreateAccountFragmentContract {

    interface  View {
        void init();
        void clickedTransitionLogin();
    }
    interface  Presenter{
        void Start();
        void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);

    }
}
