package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface ICreateAccountFragmentContract {

    interface  View {
        void init();
        void clickedTransitionLogin();
        void addSpinnerDataRestApi();
        void checkInputs();

    }
    interface  Presenter{
        void Start();
        void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment);
        void callAllCities(Activity context, Spinner spinner);
        void checkInputsCreateAccount();
        void createAccountInputData(EditText name, EditText email, EditText password, EditText phone,
                                    Spinner spinner, Button btn,Activity context);

    }
}
