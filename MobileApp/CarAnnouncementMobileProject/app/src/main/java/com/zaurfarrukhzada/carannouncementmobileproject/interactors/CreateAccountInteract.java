package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.CustomToast;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create.ICreateAccountContract;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.experimental.UtilityClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateAccountInteract {

    private ICreateAccountContract.Presenter presenter;

    public CreateAccountInteract(ICreateAccountContract.Presenter presenter) {
        this.presenter = presenter;
    }

    //CALL MYSQL DB ALL CITIES
    public void callRestApiCities(Activity context, Spinner spinner) {

        LoadingDialogCustom.startDialog(context);
        Call<List<City>> call = ManagerAll.getInstance().getInfoData();

        call.enqueue(new Callback<List<City>>() {

            @Override
            public void onResponse(@NotNull Call<List<City>> call, @NotNull Response<List<City>> response) {

                new Handler(Looper.getMainLooper()).postDelayed(LoadingDialogCustom::dismissDialog, 1200);

                List<String> cities = new ArrayList<>();
                List<Integer> cityId = new ArrayList<>();

                for (int i = 0; i < (response.body() != null ? response.body().size() : 0); i++) {
                    cities.add(response.body().get(i).getName());
                    cityId.add(i,response.body().get(i).getId());
                    presenter.allCityId(cityId);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, cities);
                spinner.setAdapter(dataAdapter);
                spinner.setPopupBackgroundResource(R.color.blueDark);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<List<City>> call, @NotNull Throwable t) {
                Log.i("Message", t.getLocalizedMessage());
            }
        });

    }
    //CREATE NEW ACCOUNT
    public void createNewAccount(User user, Activity activity) {

        SharedPreferences sharedPreferences = sharedPreferences(activity);
        String login = sharedPreferences.getString("Login", null);

        if (login == null) {
            if (!hasError(user)) {

                Call<Message> call = ManagerAll.getInstance().getCreateNewUser(user);
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {

                        assert response.body() != null;
                        presenter.onSuccess(response.body().getMessage());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Login", user.getEmail());
                        editor.apply();

                    }

                    @Override
                    public void onFailure(@NotNull Call<Message> call, @NotNull Throwable t) {
                        presenter.onFailed(Integer.parseInt(Objects.requireNonNull(t.getLocalizedMessage())));
                    }
                });

            }else{
                return;
            }
        } else {
            this.presenter.onSuccess("Qeydiyyatdan kecmisiniz.");
        }
    }

    //CHECK ACCOUNT INPUTS
    private boolean hasError(User user) {
        String name = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        long cityId = user.getCity_id();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            this.presenter.onFailed(R.string.Email_invalid);
            return true;
        }
        if (TextUtils.isEmpty(name)) {
            this.presenter.onFailed(R.string.Name_invalid);
            return true;
        }
        if(TextUtils.isEmpty(phone)){
            this.presenter.onFailed(R.string.Phone_invalid);
            return true;
        }
        if(TextUtils.isEmpty(password)){
            this.presenter.onFailed(R.string.Password_invalid);
            return true;
        }
        if(TextUtils.isEmpty(email)){
            this.presenter.onFailed(R.string.Email_Row_invalid);
            return true;
        }
        if (password.length() < 6) {
            this.presenter.onFailed(R.string.Password_length_invalid);
            return true;
        }
        if(cityId<=0){
            this.presenter.onFailed(R.string.Select_country_label);
            return true;
        }
        return false;
    }

    //SHARED PREFERENCES
    private SharedPreferences sharedPreferences(Activity activity) {
        return activity.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }


}