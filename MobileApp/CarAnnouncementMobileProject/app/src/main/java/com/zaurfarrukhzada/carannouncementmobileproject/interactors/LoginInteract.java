package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login.ILoginFragmentContract;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteract {

    ILoginFragmentContract.Presenter Loginpresenter;

    public LoginInteract(ILoginFragmentContract.Presenter loginpresenter) {
        this.Loginpresenter = loginpresenter;
    }


    public void loginUser(String email, String password, Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("Login", null);

        if (login == null) {
            if (!hasError(email, password)) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", email);
                jsonObject.addProperty("password", password);

                Call<Message> call = ManagerAll.getInstance().getLoginUser(jsonObject);
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {
                       if(response.body()!=null){

                           SharedPreferences.Editor editor = sharedPreferences.edit();
                           editor.putString("Login", email);
                           editor.apply();

                           Loginpresenter.Success(response.body().getMessage());

                       }else{
                           Loginpresenter.Failed(R.string.Response_Data);
                       }
                    }
                    @Override
                    public void onFailure(@NotNull Call<Message> call, @NotNull Throwable t) {
                        Loginpresenter.Failed(R.string.Response_Db);
                    }

                });
            } else {
                this.Loginpresenter.Failed(R.string.CheckInputs);
            }
        }

    }

    public boolean hasError(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Loginpresenter.Failed(R.string.Email_Row_invalid);
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            Loginpresenter.Failed(R.string.Password_invalid);
            return true;
        }
        return false;

    }
}
