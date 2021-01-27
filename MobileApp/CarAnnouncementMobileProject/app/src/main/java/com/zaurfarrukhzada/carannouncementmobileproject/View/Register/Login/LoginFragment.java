package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount.CreateAccountFragment;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.ForgotPassword.ForgotPasswordFragment;

public class LoginFragment extends Fragment  implements ILoginFragmentContract.View{

        LoginFragmentPresenter presenter;
        TextView transitionRegisterBtn,forgotPasswordLinkBtn;
        FrameLayout frameLayout;
        EditText loginEmailInput,loginPasswordInput;
        Button loginBtn;
        View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
          presenter = new LoginFragmentPresenter(this);
          presenter.Start();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.checkInputLoginAccount();

    }

    @Override
    public void init() {
        frameLayout = view.findViewById(R.id.Login_Frame_Layout);
        transitionRegisterBtn = view.findViewById(R.id.Login_Transition_Login);
        forgotPasswordLinkBtn = view.findViewById(R.id.Login_Forgot_Password_Link);
        loginEmailInput = view.findViewById(R.id.Login_Email_Input);
        loginPasswordInput = view.findViewById(R.id.Login_Password_Input);
        loginBtn = view.findViewById(R.id.Login_Account_Button);

    }
    @Override
    public void clickedTransitionLogin() {
       transitionRegisterBtn.setOnClickListener(v -> {
           this.presenter.setFragment(getActivity(),frameLayout,new CreateAccountFragment());
       });
    }
    @Override
    public void clickForgotPasswordLink() {
        forgotPasswordLinkBtn.setOnClickListener(v -> {
           this.presenter.setFragment(getActivity(),frameLayout,new ForgotPasswordFragment());
        });
    }

    @Override
    public void loginUser() {
        this.presenter.loginUserIsAccount(loginEmailInput,loginPasswordInput,loginBtn,getActivity());
    }


}