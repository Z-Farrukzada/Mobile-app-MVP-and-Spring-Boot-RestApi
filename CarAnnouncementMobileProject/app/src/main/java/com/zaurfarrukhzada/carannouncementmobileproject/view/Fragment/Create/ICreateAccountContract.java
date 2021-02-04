package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import com.zaurfarrukhzada.carannouncementmobileproject.model.User;

import java.util.List;

public interface ICreateAccountContract {

    interface View{

        void success(String message);

        void failed(int message);

        void showLoadingDialog();

        void hideLoadingDialog();

        void callAllCity();

        void selectCountries(List<Integer> cityId);


    }

    interface  Presenter{

        void created();

        void getAllCityList(Activity activity, Spinner spinner);

        void onSuccess(String message);

        void login(User user,Activity activity);

        void onFailed(int message);

        void allCityId(List<Integer> cityId);
    }

}
