package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;


import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.AllBrandAdapter;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.RecycleViewInterface;
import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models.ModelActivity;


import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllBrandActivity extends AppCompatActivity implements IAllBrandsContract.View, RecycleViewInterface {

    private static final int LAUNCH_A = 100;
    AllBrandsPresenter allBrandsPresenter;
    @BindView(R.id.all_brand_recycle_view) RecyclerView recyclerView;
    @BindView(R.id.all_brands_toolbar) Toolbar toolbar;
    LinearLayoutManager linearLayoutManager;
    AllBrandAdapter allBrandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_brand);
        ButterKnife.bind(this);
        allBrandsPresenter = new AllBrandsPresenter(this);
        allBrandsPresenter.created();
    }

    @Override
    public void initalize() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void showDialogLoading() {
        LoadingDialogCustom.startDialog(this);
    }

    @Override
    public void hideDialogLoading() {
       LoadingDialogCustom.dismissDialog();
    }

    @Override
    public void callAllBrands() {
        this.allBrandsPresenter.getAllBrands();
    }

    @Override
    public void recycleConfig() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getDataSuccess(List<AllCarBrand> body) {
        allBrandAdapter= new AllBrandAdapter(body, AllBrandActivity.this,this);
        recyclerView.setAdapter(allBrandAdapter);
        allBrandAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { backPress(); }
        return super.onOptionsItemSelected(item);
    }

    public void backPress(){
        Intent backPressedIntent = new Intent(this, MainActivity.class);
        startActivity(backPressedIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionSearchView(menu).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                allBrandAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public SearchView getActionSearchView(Menu menu){
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        return (SearchView) item.getActionView();
    }

    @Override
    public void onItemClicked(int id) {
        Intent passId = new Intent(this, ModelActivity.class);
        passId.putExtra("brandId",id);
        passId.putExtra("launch_target", LAUNCH_A);
        startActivity(passId);
    }
}