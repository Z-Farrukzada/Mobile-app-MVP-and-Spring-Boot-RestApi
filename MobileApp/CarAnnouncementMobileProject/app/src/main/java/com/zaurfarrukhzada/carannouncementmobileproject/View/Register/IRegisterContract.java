package com.zaurfarrukhzada.carannouncementmobileproject.View.Register;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public interface IRegisterContract {
    interface  View {
        void bindView();

        void defaultPage();

        void isDefault();
    }
    interface  Presenter{

        void Start();
        void defaultFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);

    }
}
