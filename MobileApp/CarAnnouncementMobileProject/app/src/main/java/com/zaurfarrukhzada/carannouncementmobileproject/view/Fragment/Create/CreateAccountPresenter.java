package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.interactors.CreateAccountInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;

import java.util.List;

import butterknife.BindView;

public class CreateAccountPresenter implements ICreateAccountContract.Presenter {

    ICreateAccountContract.View mView;
    CreateAccountInteract interact;

    public CreateAccountPresenter(ICreateAccountContract.View mView) {
        this.mView = mView;
        this.interact = new CreateAccountInteract(this);
    }

    @Override
    public void created() {
        this.mView.callAllCity();
    }

    @Override
    public void getAllCityList(Activity activity,Spinner spinner) {
        interact.callRestApiCities(activity,spinner);
    }

    @Override
    public void onSuccess(String message) {
         this.mView.hideLoadingDialog();
         this.mView.success(message);
    }

    @Override
    public void login(User user, Activity activity) {
          this.mView.showLoadingDialog();
          this.interact.createNewAccount(user,activity);
    }

    @Override
    public void onFailed(int message) {
        this.mView.hideLoadingDialog();
        this.mView.failed(message);
    }

    @Override
    public void allCityId(List<Integer> cityId) {
         this.mView.selectCountries(cityId);
    }


}
