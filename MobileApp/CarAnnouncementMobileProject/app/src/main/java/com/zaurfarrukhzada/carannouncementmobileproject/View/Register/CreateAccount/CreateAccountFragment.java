package com.zaurfarrukhzada.carannouncementmobileproject.View.Register.CreateAccount;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.View.Register.Login.LoginFragment;

public class CreateAccountFragment extends Fragment implements ICreateAccountFragmentContract.View {

     CreateAccountFragmentPresenter presenter;
     FrameLayout frameLayout;
     TextView transitionLoginBtn;
     View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_account, container, false);
        presenter = new CreateAccountFragmentPresenter(this);
        presenter.Start();
        return view;
    }
    @Override
    public void init() {
        frameLayout = view.findViewById(R.id.Create_Account_FrameLayout);
        transitionLoginBtn = view.findViewById(R.id.Create_Account_Transition_Login);

    }
    @Override
    public void clickedTransitionLogin() {
        transitionLoginBtn.setOnClickListener(v -> {
            this.presenter.setFragment(getActivity(),frameLayout,new LoginFragment());
        });
    }
}