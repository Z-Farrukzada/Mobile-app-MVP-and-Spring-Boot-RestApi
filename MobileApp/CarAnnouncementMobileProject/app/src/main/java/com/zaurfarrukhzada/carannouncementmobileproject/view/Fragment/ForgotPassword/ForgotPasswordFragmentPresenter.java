package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.ForgotPassword;

import android.app.Activity;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.ForgotPasswordInteract;

public class ForgotPasswordFragmentPresenter  implements IForgotPasswordFragmentContract.Presenter{

    IForgotPasswordFragmentContract.View mView;
    ForgotPasswordInteract forgotPasswordInteract;

    public ForgotPasswordFragmentPresenter(IForgotPasswordFragmentContract.View mView) {
        this.mView = mView;
        this.forgotPasswordInteract = new ForgotPasswordInteract(this);
    }

    @Override
    public void created() {

    }

    @Override
    public void changePassword(String email, String password, Activity activity) {
         this.mView.showLoadingDialog();
         this.forgotPasswordInteract.changeUserPassword(email,password,activity);
    }

    @Override
    public void success(String message) {
        this.mView.hideLoadingDialog();
        this.mView.success(message);
    }

    @Override
    public void failed(int message) {
        this.mView.hideLoadingDialog();
        this.mView.failed(message);
    }
}
