package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.CustomToast;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.ForgotPassword.IForgotPasswordFragmentContract;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordInteract {

    IForgotPasswordFragmentContract.Presenter forgotPasswordFragmentPresenter;

    public ForgotPasswordInteract(IForgotPasswordFragmentContract.Presenter forgotPasswordFragmentPresenter) {
        this.forgotPasswordFragmentPresenter = forgotPasswordFragmentPresenter;
    }


    public void changeUserPassword(String email, String password, Activity activity) {
        if(!hasError(email,password)){

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email",email);
            jsonObject.addProperty("password",password);

            if(password.trim().length()==0){

                this.forgotPasswordFragmentPresenter.failed(R.string.Please_Write_New_Password);

            }else {
                callRestApiChangePassword(jsonObject, activity);
            }
        }

    }

    private void callRestApiChangePassword(JsonObject jsonObject, Activity activity) {
        Call<Message> callChangePassword = ManagerAll.getInstance().getChangeUserPassword(jsonObject);
        callChangePassword.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {
               if(response.body()!=null){
                   forgotPasswordFragmentPresenter.success(response.body().getMessage());
               }
            }
            @Override
            public void onFailure(@NotNull Call<Message> call, @NotNull Throwable t) {
                Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean hasError(String email,String password){
        if(TextUtils.isEmpty(email)){
            this.forgotPasswordFragmentPresenter.failed(R.string.Email_Row_invalid);
            return true;
        }
        if(TextUtils.isEmpty(password)){
            this.forgotPasswordFragmentPresenter.failed(R.string.Password_invalid);
            return true;
        }
        return false;
    }
}
