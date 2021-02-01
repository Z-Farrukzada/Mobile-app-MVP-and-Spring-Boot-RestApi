package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.ForgotPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaurfarrukhzada.carannouncementmobileproject.R;

import static com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity.navController;

public class
ForgotPasswordFragment extends Fragment  {

    TextView goBackForgotPageLogin;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_forgot_password, container, false);
        initialize();
        return  view;
    }

    private void initialize(){
        goBackForgotPageLogin = view.findViewById(R.id.Forgot_Password_Go_Back);
    }


    @Override
    public void onStart() {
        super.onStart();
        goBackForgotPageLogin.setOnClickListener(v -> {
           navController.navigate(R.id.action_forgotPasswordFragment_to_loginfragment);
        });
    }
}