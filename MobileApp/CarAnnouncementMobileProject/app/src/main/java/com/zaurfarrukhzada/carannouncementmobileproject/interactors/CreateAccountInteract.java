package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@UtilityClass
public class CreateAccountInteract {

    public void callRestApiCities(Activity context, Spinner spinner) {

        LoadingDialogCustom.startDialog(context);
        Call<List<City>> call = ManagerAll.getInstance().getInfoData();
        call.enqueue(new Callback<List<City>>() {

            @Override
            public void onResponse(@NotNull Call<List<City>> call, @NotNull Response<List<City>> response) {

                new Handler(Looper.getMainLooper()).postDelayed(LoadingDialogCustom::dismissDialog, 1200);

                List<String> cities = new ArrayList<>();

                for (int i = 0; i < (response.body() != null ? response.body().size() : 0); i++) {
                    cities.add(response.body().get(i).getName());
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

    public void selectCountries(Activity context, Spinner countries, EditText name,
                                EditText email, EditText password, EditText phone, Button btn) {

         SharedPreferences sharedPreferences = sharedPreferences(context);
         String login =  sharedPreferences.getString("Login", null);;

        if (login == null) {

            countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    User user = callUser(name,email,password,phone);

                    String item = parent.getItemAtPosition(position).toString();
                    Call<Long> call = ManagerAll.getInstance().getWithByNameId(item);
                    call.enqueue(new Callback<Long>() {
                        @Override
                        public void onResponse(@NotNull Call<Long> call, @NotNull Response<Long> response) {
                            user.setCity_id(response.body());
                            btn.setEnabled(true);

                            btn.setOnClickListener(v -> {

                                if (!TextUtils.isEmpty(name.getText())
                                        && Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()
                                        && !TextUtils.isEmpty(password.getText())
                                        && password.length() >= 6) {
                                    TextUtils.isEmpty(phone.getText());

                                    LoadingDialogCustom.startDialog(context);

                                    Call<Message> registerMessage = ManagerAll.getInstance().getCreateNewUser(user);
                                    registerMessage.enqueue(new Callback<Message>() {
                                        @Override
                                        public void onResponse(@NotNull Call<Message> call, @NotNull Response<Message> response) {

                                            ToastMessage(context,response.body().getMessage(),R.drawable.happy);

                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("Login", email.getText().toString());
                                            editor.apply();

                                            LoadingDialogCustom.dismissDialog();
                                            callIntent(context);
                                        }

                                        @Override
                                        public void onFailure(Call<Message> call, Throwable t) {
                                          ToastMessage(context,"Zəhmət olmasa hər şeyi tam daxil edin.",R.drawable.angry);
                                        }
                                    });
                                }else{
                                    ToastMessage(context,"Zəhmət olmasa hər şeyi tam daxil edin.",R.drawable.angry);
                                }
                            });
                        }
                        @Override
                        public void onFailure(@NotNull Call<Long> call, @NotNull Throwable t) {
                            Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
           callIntent(context);
        }
    }

    //CUSTOM TOAST
    private void ToastMessage(Activity activity,String text,int image){
       View view = CustomToast.showMessage(activity,R.layout.custom_toast_success);
        view.findViewById(R.id.custom_toast_root).setBackgroundTintList(ContextCompat.getColorStateList(activity,R.color.blueDark));
        CustomToast.setToastTextAndImage(view,R.id.success_toast_message_text,text,R.id.custom_toast_image,image);
        CustomToast.toastProperty(activity, Gravity.AXIS_CLIP,0,600,1000,view);

    }

    //SHARED PREFERENCES
    private SharedPreferences sharedPreferences(Activity activity){
               return  activity.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    //CALL USER
    private User callUser(EditText name,EditText email,EditText password,EditText phone){
        return User.builder()
                .username(name.getText().toString())
                .email(email.getText().toString())
                .password(password.getText().toString())
                .phone(phone.getText().toString())
                .build();
    }

    //INTENT
    public void callIntent(Activity context){
        Intent mainIntent = new Intent(context, MainActivity.class);
        context.startActivity(mainIntent);
        context.finish();
    }


}
