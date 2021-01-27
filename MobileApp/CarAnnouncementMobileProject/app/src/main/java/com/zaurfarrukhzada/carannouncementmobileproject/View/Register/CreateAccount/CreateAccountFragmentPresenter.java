package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zaurfarrukhzada.carannouncementmobileproject.Model.City;
import com.zaurfarrukhzada.carannouncementmobileproject.Custom.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.Message;
import com.zaurfarrukhzada.carannouncementmobileproject.Model.User;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.RestApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Home.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountFragmentPresenter implements ICreateAccountFragmentContract.Presenter {

    ICreateAccountFragmentContract.View mView;

    public CreateAccountFragmentPresenter(ICreateAccountFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void Start() {
        this.mView.init();
        this.mView.clickedTransitionLogin();
        this.mView.addSpinnerDataRestApi();
    }

    @Override
    public void setFragment(Activity activity, FrameLayout frameLayout, Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_out, R.anim.fade_in);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void callAllCities(Activity context, Spinner spinner) {

        LoadingDialogCustom.startDialog(context);
        Call<List<City>> call = ManagerAll.getInstance().getInfoData();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                LoadingDialogCustom.dismissDialog();
                List<String> cities = new ArrayList<>();
                for (int i = 0; i < response.body().size(); i++) {
                    cities.add(response.body().get(i).getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, cities);
                spinner.setAdapter(dataAdapter);
                spinner.setPopupBackgroundResource(R.color.blueDark);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.i("Message", t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void checkInputsCreateAccount() {
        this.mView.checkInputs();

    }

    @Override
    public void createAccountInputData(EditText name, EditText email, EditText password, EditText phone
            , Spinner countires, Button btn, Activity context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("Login",null);

        if(login == null) {
            List<EditText> objectList = new ArrayList<>();
            objectList.add(name);
            objectList.add(email);
            objectList.add(password);
            objectList.add(phone);
            for (int i = 0; i < objectList.size(); i++) {
                objectList.get(i).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(name.getText())) {
                            if (!TextUtils.isEmpty(email.getText())) {
                                if (!TextUtils.isEmpty(phone.getText())) {
                                    btn.setEnabled(!(TextUtils.isEmpty(password.getText()) && password.length() >= 6));
                                    countires.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                            User user = User.builder()
                                                    .username(name.getText().toString())
                                                    .email(email.getText().toString())
                                                    .password(password.getText().toString())
                                                    .phone(phone.getText().toString())
                                                    .build();

                                            String item = parent.getItemAtPosition(position).toString();
                                            Call<Long> call = ManagerAll.getInstance().getWithByNameId(item);
                                            call.enqueue(new Callback<Long>() {
                                                @Override
                                                public void onResponse(@NotNull Call<Long> call, @NotNull Response<Long> response) {
                                                    user.setCity_id(response.body());
                                                    btn.setOnClickListener(v -> {

                                                       LoadingDialogCustom.startDialog(context);

                                                        Call<Message> registerMessage = ManagerAll.getInstance().getCreateNewUser(user);
                                                        registerMessage.enqueue(new Callback<Message>() {
                                                            @Override
                                                            public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {
                                                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                editor.putString("Login", email.getText().toString());
                                                                editor.apply();

                                                                Intent mainIntent = new Intent(context, MainActivity.class);
                                                                LoadingDialogCustom.dismissDialog();
                                                                context.startActivity(mainIntent);
                                                                context.finish();
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Message> call, Throwable t) {
                                                                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                                            }
                                                        });
                                                    });
                                                }

                                                @Override
                                                public void onFailure(@NotNull Call<Long> call, Throwable t) {
                                                    Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {
                                            // TODO Auto-generated method stub
                                        }
                                    });
                                } else {
                                    btn.setEnabled(false);
                                }
                            } else {
                                btn.setEnabled(false);
                            }
                        } else {
                            btn.setEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }else{
            Intent mainIntent = new Intent(context,MainActivity.class);
            context.startActivity(mainIntent);
            context.finish();
        }
    }
}
