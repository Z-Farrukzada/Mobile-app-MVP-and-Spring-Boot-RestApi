package com.zaurfarrukhzada.carannouncementmobileproject.Contract;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public interface IRegisterContract {

    interface  View {
        void DefaultFragment();

    }
    interface  Presenter{
        void Start();
        void setFragment(Fragment fragment, Activity activity, FrameLayout frameLayout);
    }

}
