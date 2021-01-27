package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.ForgotPassword;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.icu.text.CompactDecimalFormat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.zaurfarrukhzada.carannouncementmobileproject.Custom.CustomToast;
import com.zaurfarrukhzada.carannouncementmobileproject.Custom.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.Custom.WithInputDialog;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.RestApi.ManagerAll;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter implements  IForgotPasswordContract.Presenter {

    IForgotPasswordContract.View mView;

    public ForgotPasswordPresenter(IForgotPasswordContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
        this.mView.init();
        this.mView.goBackLoginPage();
    }

    @Override
    public void setLoginFragment(Activity activity, FrameLayout frameLayout, Fragment fragment) {
        FragmentTransaction fragmentTransaction =((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.slide_out,R.anim.fade_in);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void checkForgotEmailInput() {
        this.mView.checkEmail();
    }

    @Override
    public void sendEmailAndChangePassword(EditText forgotEmailInput, Button forgotBtn, Activity activity, View view) {

        forgotEmailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  TODO BEFORE TEXT CHANGED
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(forgotEmailInput.getText())) {
                    forgotBtn.setEnabled(true);

                    forgotBtn.setOnClickListener(v -> {

                        AlertDialog alertDialog = WithInputDialog.showDialog(activity,R.layout.password_change_layout);

                        EditText passwordText = (EditText) alertDialog.findViewById(R.id.password_change_dialog);
                        Button yesBtn = alertDialog.findViewById(R.id.Button_Yes_Dialog);
                        Button noBtn = alertDialog.findViewById(R.id.Button_No_Dialog);


                         yesBtn.setOnClickListener(v1 -> {

                             LoadingDialogCustom.startDialog(activity);
                             alertDialog.dismiss();
                             JsonObject jsonObject = new JsonObject();
                             jsonObject.addProperty("email", forgotEmailInput.getText().toString());
                             jsonObject.addProperty("password",passwordText.getText().toString());

                             if(passwordText.getText().toString().trim().length()==0){

                                 View toastView = CustomToast.showMessage(activity,R.layout.custom_toast_success);
                                 CustomToast.setToastTextAndImage(toastView,R.id.success_toast_message_text,R.string.Please_Write_New_Password
                                         ,R.id.custom_toast_image,R.drawable.angry);
                                 toastView.findViewById(R.id.custom_toast_root).setBackgroundTintList(ContextCompat.getColorStateList(activity,R.color.error));
                                 CustomToast.toastProperty(activity, Gravity.AXIS_CLIP,0,600,3000,toastView);

                                 LoadingDialogCustom.dismissDialog();
                             }else {

                                 callRestApiChangePassword(jsonObject, activity);
                                 passwordText.setText(" ");
                             }

                         });
                         noBtn.setOnClickListener(v1 -> {
                           alertDialog.dismiss();
                         });
                    });
                }else{
                    forgotBtn.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //TODO: AFTER CHANGE TEXT
            }

        });

    }

    private void callRestApiChangePassword(JsonObject jsonObject,Activity activity) {
        Call<Message> callChangePassword = ManagerAll.getInstance().getChangeUserPassword(jsonObject);
        callChangePassword.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {
              LoadingDialogCustom.dismissDialog();
                assert response.body() != null;
                Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(@NotNull Call<Message> call, @NotNull Throwable t) {
                Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

