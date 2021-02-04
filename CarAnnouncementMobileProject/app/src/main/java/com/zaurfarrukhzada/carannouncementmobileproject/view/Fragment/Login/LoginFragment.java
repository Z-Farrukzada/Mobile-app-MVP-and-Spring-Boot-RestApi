package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.CustomToast;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity.navController;


public class LoginFragment extends Fragment implements  ILoginFragmentContract.View{

    private  LoginFragmentPresenter loginFragmentPresenter;
    @BindView(R.id.Login_Email_Input)
    EditText emailInput;
    @BindView(R.id.Login_Password_Input)
    EditText passwordInput;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        loginFragmentPresenter = new LoginFragmentPresenter(this);
        loginFragmentPresenter.created();
        return  view;
    }

    //TRANSITION LOGIN FRAGMENT TO CREATE ACCOUNT FRAGMENT
    @OnClick(R.id.Login_Transition_Login)
    public void loginTransactionCreateAccountPage(){
        navController.navigate(R.id.action_loginfragment_to_createAccountFragment);
    }

    //TRANSITION LOGIN FRAGMENT TO FORGOT PASSWORD FRAGMENT
    @OnClick(R.id.Login_Forgot_Password_Link)
    public void loginTransactionForgotPage(){
        navController.navigate(R.id.action_loginfragment_to_forgotPasswordFragment);
    }

    //LOGIN USER
    @OnClick(R.id.Login_Account_Button)
    public void loginUserBtn(){
         String email = emailInput.getText().toString();
         String password = passwordInput.getText().toString();
         this.loginFragmentPresenter.login(email,password,getActivity());
    }


    //SUCCESS MESSAGE
    @Override
    public void Success(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        IntentLoginAccount();
    }

    //FAILED MESSAGE
    @Override
    public void Failed(int message) {
        loginToast(message,R.drawable.angry);
    }


    //SHOW LOADING DIALOG
    @Override
    public void showLoadingDialog() {
        LoadingDialogCustom.startDialog(getActivity());
    }

    //HIDE LOADING DIALOG
    @Override
    public void hideLoadingDialog() {
       LoadingDialogCustom.dismissDialog();
    }

    //CUSTOM TOAST
    public void loginToast(int message,int image) {
        View view = CustomToast.showMessage(requireActivity(),R.layout.custom_toast_success);
        view.findViewById(R.id.custom_toast_root).setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.blueDark));
        CustomToast.setToastTextAndImage(view,R.id.success_toast_message_text,message,R.id.custom_toast_image,image);
        CustomToast.toastProperty(requireActivity(), Gravity.AXIS_CLIP,0,600,1000,view);
    }

    //CHECK USER LOGIN
    @Override
    public void checkSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String login  = sharedPreferences.getString("Login",null);
        if(login != null){
            loginToast(R.string.Welcome_back,R.drawable.happy);
            IntentLoginAccount();
        }
    }

    //SHARED INTENT
    public void IntentLoginAccount(){
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }



}