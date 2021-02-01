package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

public interface ICreateAccountContract {

    interface View{

        void callAllCity();

        void createAccount();

    }

    interface  Presenter{

        void created();

        void getAllCityList(Activity activity, Spinner spinner);

        void onViewCreatedFrag();

        void createNewUser(EditText username, EditText email, EditText phone, EditText password, Spinner countries, Button btn,Activity activity);
    }

}
