package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Login;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zaurfarrukhzada.carannouncementmobileproject.R;

import static com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity.navController;


public class LoginFragment extends Fragment {

   TextView transitionForgotPassword,loginTransitionCreateBtn;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        initialize();
        return  view;
    }

    private void initialize(){
        loginTransitionCreateBtn = view.findViewById(R.id.Login_Transition_Login);
        transitionForgotPassword = view.findViewById(R.id.Login_Forgot_Password_Link);
    }

    @Override
    public void onStart() {
        super.onStart();
        loginTransitionCreateBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginfragment_to_createAccountFragment);
        });
        transitionForgotPassword.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginfragment_to_forgotPasswordFragment);
        });

    }
}