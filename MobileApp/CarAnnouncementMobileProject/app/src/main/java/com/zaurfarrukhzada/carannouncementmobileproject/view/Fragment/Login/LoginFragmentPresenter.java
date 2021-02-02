package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.LoginInteract;

public class LoginFragmentPresenter implements ILoginFragmentContract.Presenter {

    ILoginFragmentContract.View mView;
    LoginInteract loginInteract;

    public LoginFragmentPresenter(ILoginFragmentContract.View mView) {
        this.mView = mView;
        loginInteract = new LoginInteract(this);
    }

    @Override
    public void created() {
        this.mView.checkSharedPreferences();
    }

    @Override
    public void login(String email, String password, Activity activity) {
        this.mView.showLoadingDialog();
        this.loginInteract.loginUser(email,password,activity);
    }

    @Override
    public void Success(String message) {
        this.mView.hideLoadingDialog();
        this.mView.Success(message);
    }

    @Override
    public void Failed(int message) {
        this.mView.hideLoadingDialog();
        this.mView.Failed(message);
    }
}
