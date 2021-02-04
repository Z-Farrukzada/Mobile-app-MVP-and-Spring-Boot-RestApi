package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.BrandAdapter;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements IMainActivityContract.View{

    MainActivityPresenter mainActivityPresenter;
    @BindView(R.id.navigation_view_menu) NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    @BindView(R.id.drawer_main) DrawerLayout drawerLayout;
    @BindView(R.id.main_menu_toolbar) Toolbar toolbar;
    @BindView(R.id.brands_recycle) RecyclerView recyclerView;
    BrandAdapter brandAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenter(this);
        this.mainActivityPresenter.created();
        
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void brandRecycleConfig() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onGetDataSuccess(List<CarBrand> carBrandList) {
        brandAdapter = new BrandAdapter(carBrandList,MainActivity.this);
        recyclerView.setAdapter(brandAdapter);
    }

    @Override
    public void getAllBrands() {
        this.mainActivityPresenter.callAllBrands();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if(item.getItemId()==R.id.nav_wallet){
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if(item.getItemId()==R.id.nav_share){
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });
    }
}
