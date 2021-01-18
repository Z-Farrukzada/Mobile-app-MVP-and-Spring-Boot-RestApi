package com.zaurfarrukhzada.carannouncementmobileproject.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaurfarrukhzada.carannouncementmobileproject.R;

public class CreateAccountFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        return view;
    }
}