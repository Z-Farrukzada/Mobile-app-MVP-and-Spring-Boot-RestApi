package com.zaurfarrukhzada.carannouncementmobileproject.view.Fragment.Create;


import android.app.blob.BlobStoreManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.User;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.CustomToast;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivity;

import java.util.List;
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
    @BindView(R.id.Create_Account_Name_Input)
    EditText name;
    @BindView(R.id.Create_Account_Email_Input)
    EditText email;
    @BindView(R.id.Create_Account_Phone_Input)
    EditText phone;
    @BindView(R.id.Create_Account_Password_Input)
    EditText password;
    Long chooseCountries;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        ButterKnife.bind(this, view);
        this.createAccountPresenter = new CreateAccountPresenter(this);
        this.createAccountPresenter.created();

        return view;
    }



    @OnClick(R.id.Create_Account_Transition_Login)
    public void clicked() {
        navController.navigate(R.id.action_createAccountFragment_to_loginfragment);
    }

    @OnClick(R.id.Create_Account_Button)
    public void createBtn(){
        User user = User.builder()
                .username(name.getText().toString())
                .email(email.getText().toString())
                .password(password.getText().toString())
                .phone(phone.getText().toString())
                .city_id(chooseCountries)
                .build();

        this.createAccountPresenter.login(user,getActivity());
    }


    @Override
    public void selectCountries(List<Integer> cityId) {
        countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 chooseCountries = cityId.get(position).longValue();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public void success(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
        IntentCreateAccount();
    }

    @Override
    public void failed(int message) {
        CustomToast(message,R.drawable.angry);
    }

    //SHOW DIALOG
    @Override
    public void showLoadingDialog() {
        LoadingDialogCustom.startDialog(getActivity());
    }

    //HIDE DIALOG
    @Override
    public void hideLoadingDialog() {
        LoadingDialogCustom.dismissDialog();
    }

    //GET ALL CITIES
    @Override
    public void callAllCity() {
        this.createAccountPresenter.getAllCityList(getActivity(), countries);
    }

    //CUSTOM TOAST
    @Override
    public  void CustomToast(int message,int image) {
        View view = CustomToast.showMessage(requireActivity(),R.layout.custom_toast_success);
        view.findViewById(R.id.custom_toast_root).setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.blueDark));
        CustomToast.setToastTextAndImage(view,R.id.success_toast_message_text,message,R.id.custom_toast_image,image);
        CustomToast.toastProperty(requireActivity(), Gravity.AXIS_CLIP,0,600,1000,view);
    }
    //SHARED INTENT
    public void IntentCreateAccount(){
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

}