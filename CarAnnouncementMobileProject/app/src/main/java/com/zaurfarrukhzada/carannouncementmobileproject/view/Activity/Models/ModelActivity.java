package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.ModelAdapter;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands.AllBrandActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModelActivity extends AppCompatActivity implements IModelContract.View {


    ModelPresenter modelPresenter;
    @BindView(R.id.car_model_recycle_view)
    RecyclerView modelRecycleView;
    @BindView(R.id.car_model_toolbar)
    Toolbar toolbar;
    LinearLayoutManager linearLayoutManager;
    ModelAdapter modelAdapter;
    int target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        ButterKnife.bind(this);
        modelPresenter = new ModelPresenter(this);
        modelPresenter.created();
    }

    @Override
    public void init() {
        Intent getId = getIntent();
        int id = getId.getIntExtra("brandId", 0);
        target = getId.getIntExtra("launch_target", 0);
        this.modelPresenter.getFindModel(id, this);
    }

    @Override
    public void layoutInit() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        modelRecycleView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void supportToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void success(List<CarModel> body) {
        modelAdapter = new ModelAdapter(body);
        modelRecycleView.setAdapter(modelAdapter);
        modelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingDialog() {
        LoadingDialogCustom.startDialog(this);
    }


    @Override
    public void hideLoadingDialog() {
        LoadingDialogCustom.dismissDialog();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (target != 100) {
                startActivity(backPressMain());
            } else {
                startActivity(backPressAll());
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private Intent backPressAll() {
        return new Intent(this, AllBrandActivity.class);
    }


    private Intent backPressMain() {
        return new Intent(this, MainActivity.class);
    }


}


