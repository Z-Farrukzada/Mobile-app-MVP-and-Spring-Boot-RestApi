package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.Custom.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.RestApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Home.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragmentPresenter implements ILoginFragmentContract.Presenter {

    ILoginFragmentContract.View mView;

    public LoginFragmentPresenter(ILoginFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
        this.mView.init();
        this.mView.clickForgotPasswordLink();
        this.mView.clickedTransitionLogin();

    }

    @Override
    public void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_out, R.anim.fade_in);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void checkInputLoginAccount() {
        this.mView.loginUser();
    }

    @Override
    public void loginUserIsAccount(EditText loginEmailInput, EditText loginPasswordInput, Button loginBtn, Activity context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("Login", null);

        if (login == null) {

            List<EditText> list = new ArrayList<>();
            list.add(loginEmailInput);
            list.add(loginPasswordInput);

            for (int i = 0; i < list.size(); i++) {

                list.get(i).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //TODO:BEFORE TEXT CHANGED
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(loginEmailInput.getText())) {
                            if (!TextUtils.isEmpty(loginPasswordInput.getText())) {
                                loginBtn.setEnabled(true);

                                String email = loginEmailInput.getText().toString();
                                String password = loginPasswordInput.getText().toString();

                                loginBtn.setOnClickListener(v -> {
                                   LoadingDialogCustom.startDialog(context);

                                    JsonObject jsonObject = new JsonObject();
                                    jsonObject.addProperty("email", email);
                                    jsonObject.addProperty("password", password);

                                    Call<Message> loginCall = ManagerAll.getInstance().getLoginUser(jsonObject);
                                    loginCall.enqueue(new Callback<Message>() {

                                        @Override
                                        public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {

                                            if (response.body() != null) {
                                                LoadingDialogCustom.dismissDialog();
                                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("Login", email);
                                                editor.apply();

                                                Intent loginIntent = new Intent(context, MainActivity.class);
                                                context.startActivity(loginIntent);
                                                context.finish();
                                            } else {
                                                Toast.makeText(context, "Email ve Şifrə səhvdir.", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Message> call, Throwable t) {
                                            Log.i("LoginFailureMessage", t.getLocalizedMessage());
                                        }
                                    });
                                });

                            } else {
                                loginBtn.setEnabled(false);
                            }
                        } else {
                            loginBtn.setEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //TODO:AFTER TEXT CHANGED
                    }
                });
            }

        }else{
            Intent mainIntent = new Intent(context, MainActivity.class);
            context.startActivity(mainIntent);
            context.finish();
        }
    }

}
