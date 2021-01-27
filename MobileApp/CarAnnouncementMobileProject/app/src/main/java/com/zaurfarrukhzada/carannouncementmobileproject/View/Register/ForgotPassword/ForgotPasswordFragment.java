package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.ForgotPassword;

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
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login.LoginFragment;

public class ForgotPasswordFragment extends Fragment implements IForgotPasswordContract.View {

    ForgotPasswordPresenter presenter;
    TextView goBack;
    FrameLayout forgotFrameLayout;
    View view;
    EditText forgotEmailInput,dialogInputPassword;
    Button forgotBtn,forgotNoBnt,forgotYesBnt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_forgot_password, container, false);
        presenter = new ForgotPasswordPresenter(this);
        presenter.Start();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.checkForgotEmailInput();

    }

    @Override
    public void init() {
        forgotFrameLayout = view.findViewById(R.id.Forgot_Password_Layout);
        goBack = view.findViewById(R.id.Forgot_Password_Go_Back);
        forgotEmailInput =(EditText) view.findViewById(R.id.Forgot_Password_Email_Input);
        forgotBtn =(Button) view.findViewById(R.id.Forgot_Password_Button);


    }

    @Override
    public void goBackLoginPage() {
        goBack.setOnClickListener(v -> {
            this.presenter.setLoginFragment(getActivity(),forgotFrameLayout,new LoginFragment());
        });
    }

    @Override
    public void checkEmail() {
        this.presenter.sendEmailAndChangePassword(forgotEmailInput,forgotBtn,getActivity(),getView());
    }

}