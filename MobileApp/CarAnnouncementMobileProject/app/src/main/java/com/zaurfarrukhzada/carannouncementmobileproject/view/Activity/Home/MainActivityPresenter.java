package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

public class MainActivityPresenter  implements IMainActivityContract.Presenter{

    IMainActivityContract.View mView;

    public MainActivityPresenter(IMainActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void created() {
        this.mView.init();
    }
}
