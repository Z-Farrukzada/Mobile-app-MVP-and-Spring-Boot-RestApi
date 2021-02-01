package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.interactors.CreateAccountInteract;

import butterknife.BindView;

public class CreateAccountPresenter implements ICreateAccountContract.Presenter {

    ICreateAccountContract.View mView;

    public CreateAccountPresenter(ICreateAccountContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void created() {
        this.mView.callAllCity();
    }

    @Override
    public void onViewCreatedFrag() {
       this.mView.createAccount();
    }

    @Override
    public void createNewUser(EditText username, EditText email, EditText phone, EditText password, Spinner countries, Button btn, Activity activity) {
        CreateAccountInteract.selectCountries(activity,countries,username,email,password,phone,btn);
    }

    @Override
    public void getAllCityList(Activity activity,Spinner spinner) {
        CreateAccountInteract.callRestApiCities(activity,spinner);
    }

}
