package com.zaurfarrukhzada.carannouncementmobileproject.Contract;

import android.app.Activity;


public interface ILoadingContract {

    interface  View {
        void LoadingTransitionMain();
    }
    interface  Presenter {
        void Start();
        void StartLoading(Activity context);
    }

}
