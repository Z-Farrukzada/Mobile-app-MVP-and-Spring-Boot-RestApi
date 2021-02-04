package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.ForgotPassword;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.WithInputDialog;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login.LoginFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity.navController;

public class ForgotPasswordFragment extends Fragment implements IForgotPasswordFragmentContract.View {

    ForgotPasswordFragmentPresenter forgotPasswordFragmentPresenter;
    @BindView(R.id.Forgot_Password_Email_Input)
    EditText emailInput;
    View view;
    AlertDialog alertDialog;
    EditText passwordText;
    Button yesBtn,noBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_forgot_password, container, false);
        ButterKnife.bind(this,view);
        forgotPasswordFragmentPresenter = new ForgotPasswordFragmentPresenter(this);
        forgotPasswordFragmentPresenter.created();
        return  view;
    }
    //FORGOT PASSWORD FRAGMENT TRANSITION LOGIN FRAGMENT
    @OnClick(R.id.Forgot_Password_Go_Back)
    public void goBack(){
        navController.navigate(R.id.action_forgotPasswordFragment_to_loginfragment);
    }

    //FORGOT PASSWORD BUTTON CLICKED
    @OnClick(R.id.Forgot_Password_Button)
    public void forgotBtn(){
        alertDialog = WithInputDialog.showDialog(getActivity(),R.layout.password_change_layout);
        passwordText =   alertDialog.findViewById(R.id.password_change_dialog);

        yesBtn = alertDialog.findViewById(R.id.Button_Yes_Dialog);
        noBtn = alertDialog.findViewById(R.id.Button_No_Dialog);

        yesBtn.setOnClickListener(v -> {alertDialogYesBtn();});
        noBtn.setOnClickListener(v -> {alertDialogNoBtn();});

        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    //DIALOG YES BTN
    private void alertDialogYesBtn() {
         String email = emailInput.getText().toString();
         String password = passwordText.getText().toString();
         this.forgotPasswordFragmentPresenter.changePassword(email,password,getActivity());
    }

    //DIALOG NO BTN
    private void alertDialogNoBtn() {
        alertDialog.dismiss();
    }

    //CUSTOM LOADING SHOW
    @Override
    public void showLoadingDialog() {
        LoadingDialogCustom.startDialog(getActivity());
    }

    //CUSTOM DIALOG HIDE
    @Override
    public void hideLoadingDialog() {
       LoadingDialogCustom.dismissDialog();
       alertDialog.dismiss();
    }

    //CHANGE PASSWORD SUCCESS
    @Override
    public void success(String message) {
       Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
       loginIntent();
    }

    //CHANGE PASSWORD FAILURE
    @Override
    public void failed(int message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    //SUCCESS CHANGE INTENT LOGIN
    public void loginIntent(){
        navController.navigate(R.id.action_forgotPasswordFragment_to_loginfragment);
    }
}