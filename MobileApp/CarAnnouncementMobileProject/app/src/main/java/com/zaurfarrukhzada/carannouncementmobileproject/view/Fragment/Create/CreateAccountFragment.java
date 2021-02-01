package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.zaurfarrukhzada.carannouncementmobileproject.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register.RegisterActivity.navController;


public class CreateAccountFragment extends Fragment implements ICreateAccountContract.View {

    CreateAccountPresenter createAccountPresenter;

    @BindView(R.id.Create_Account_Transition_Login)
    TextView createAccountTransitionLogin;
    @BindView(R.id.Create_Account_Spinner)
    Spinner countries;
    @BindView(R.id.Create_Account_Button)
    Button createAccountBtn;
    @BindView(R.id.Create_Account_Name_Input)
    EditText name;
    @BindView(R.id.Create_Account_Email_Input)
    EditText email;
    @BindView(R.id.Create_Account_Phone_Input)
    EditText phone;
    @BindView(R.id.Create_Account_Password_Input)
    EditText password;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        ButterKnife.bind(this, view);
        this.createAccountPresenter = new CreateAccountPresenter(this);
        this.createAccountPresenter.created();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.createAccountPresenter.onViewCreatedFrag();

    }

    @OnClick(R.id.Create_Account_Transition_Login)
    public void clicked() {
        navController.navigate(R.id.action_createAccountFragment_to_loginfragment);
    }

    @Override
    public void callAllCity() {
        this.createAccountPresenter.getAllCityList(getActivity(), countries);
    }

    @Override
    public void createAccount() {
        this.createAccountPresenter.createNewUser(name, email, phone, password, countries, createAccountBtn, getActivity());
    }


}