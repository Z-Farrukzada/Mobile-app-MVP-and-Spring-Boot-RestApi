package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.zaurfarrukhzada.carannouncementmobileproject.Model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.RestApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Home.MainActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountFragment extends Fragment implements ICreateAccountFragmentContract.View {

     CreateAccountFragmentPresenter presenter;
     FrameLayout frameLayout;
     TextView transitionLoginBtn;
     View view;
     EditText name,email,password,phone;
     Spinner countries;
     Button createAccountBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_account, container, false);
        presenter = new CreateAccountFragmentPresenter(this);
        presenter.Start();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.checkInputsCreateAccount();
    }

    @Override
    public void init() {
        frameLayout = view.findViewById(R.id.Create_Account_FrameLayout);
        transitionLoginBtn = view.findViewById(R.id.Create_Account_Transition_Login);
        name = view.findViewById(R.id.Create_Account_Name_Input);
        email = view.findViewById(R.id.Create_Account_Email_Input);
        password =view.findViewById(R.id.Create_Account_Password_Input);
        phone = view.findViewById(R.id.Create_Account_Phone_Input);
        countries = view.findViewById(R.id.Create_Account_Spinner);
        createAccountBtn = view.findViewById(R.id.Create_Account_Button);

    }
    @Override
    public void clickedTransitionLogin() {
        transitionLoginBtn.setOnClickListener(v -> {
            this.presenter.setFragment(getActivity(),frameLayout,new LoginFragment());
        });
    }

    @Override
    public void addSpinnerDataRestApi() {
        this.presenter.callAllCities(getActivity(),countries);
     }

    @Override
    public void checkInputs() {
        this.presenter.createAccountInputData(name,email,password,phone,countries,createAccountBtn,getActivity());
    }


}