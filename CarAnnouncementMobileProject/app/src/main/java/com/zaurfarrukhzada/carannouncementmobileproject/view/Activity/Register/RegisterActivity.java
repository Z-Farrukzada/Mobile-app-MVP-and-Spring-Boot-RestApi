package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.zaurfarrukhzada.carannouncementmobileproject.R;


public class RegisterActivity extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    public  static  NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       navController = Navigation.findNavController(this,R.id.nav_host);
    }
}