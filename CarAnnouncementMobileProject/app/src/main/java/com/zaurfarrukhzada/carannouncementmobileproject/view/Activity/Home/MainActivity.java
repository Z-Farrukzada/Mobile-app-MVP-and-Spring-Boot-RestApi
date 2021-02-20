package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.BrandAdapter;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.RecycleViewInterface;
import com.zaurfarrukhzada.carannouncementmobileproject.adapter.SliderAdapter;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.LoadingDialogCustom;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands.AllBrandActivity;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Models.ModelActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements IMainActivityContract.View, RecycleViewInterface ,
        BottomNavigationView.OnNavigationItemSelectedListener {

    MainActivityPresenter mainActivityPresenter;
    @BindView(R.id.navigation_view_menu) NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    @BindView(R.id.drawer_main) DrawerLayout drawerLayout;
    @BindView(R.id.main_menu_toolbar) Toolbar toolbar;
    @BindView(R.id.brands_recycle) RecyclerView recyclerView;
    @BindView(R.id.bottom_navigation_view) BottomNavigationView bottomNavigationView;
    @BindView(R.id.main_viewpager2_slider) ViewPager2 viewPager2;
    BrandAdapter brandAdapter;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler(Looper.getMainLooper());

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
        brandAdapter = new BrandAdapter(carBrandList,MainActivity.this,this);
        recyclerView.setAdapter(brandAdapter);
    }

    @Override
    public void getAllBrands() {
        this.mainActivityPresenter.callAll();
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
    public void onGetDatSlider(List<SliderItem> sliderItems) {
        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @OnClick(R.id.all_brand)
    public void transitionAllBrandActivity(){
        Intent allActivityIntent = new Intent(this, AllBrandActivity.class);
        startActivity(allActivityIntent);
    }

    @Override
    public void bottomNavConfig() {
        bottomNavigationView.setSelectedItemId(R.id.nav_add);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    Runnable runnable = () -> viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);


    @Override
    public void slideConfig() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               handler.removeCallbacks(runnable);
               handler.postDelayed(runnable,4000);//Slide Duration
           }
       });
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

    @Override
    public void onItemClicked(int id) {
       Intent passingId = new Intent(this, ModelActivity.class);
       passingId.putExtra("brandId",id);
       startActivity(passingId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //TODO:SWITCH OR IF check item id and transition items
        return false;
    }


}
