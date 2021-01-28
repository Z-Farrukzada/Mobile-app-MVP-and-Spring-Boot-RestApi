package com.zaurfarrukhzada.carannouncementmobileproject.View.Home;

public class MainPresenter implements  IMainContract.Presenter{

    IMainContract.View mView;

    public MainPresenter(IMainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void init() {
        this.mView.initialize();
    }
}
