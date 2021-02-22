package com.zaurfarrukhzada.carannouncementmobileproject.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Announcement;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.Convert64;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnouncementPopAdapter extends RecyclerView.Adapter<AnnouncementPopAdapter.ViewHolder> {

    List<Announcement> announcementList;

    public AnnouncementPopAdapter(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    @NonNull
    @Override
    public AnnouncementPopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_announcement_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementPopAdapter.ViewHolder holder, int position) {

           String exChange = announcementList.get(position).getExchangeName();
           String modelName = announcementList.get(position).getModelName();
           int Price = announcementList.get(position).getPrice();
           String imageView = announcementList.get(position).getImageUrl();
           String brandName = announcementList.get(position).getBrandName();
           int Walk = announcementList.get(position).getWalk();
           String Year = announcementList.get(position).getCarYear();

           holder.setData(exChange,brandName,modelName,Walk,Price,Year);
           holder.setImage(imageView);
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.car_announcement_image) ImageView imageView;
        @BindView(R.id.car_announcement_exchange) TextView exChange;
        @BindView(R.id.car_announcement_model) TextView model;
        @BindView(R.id.car_announcement_price) TextView price;
        @BindView(R.id.car_announcement_walk) TextView walk;
        @BindView(R.id.car_announcement_brand) TextView brand;
        @BindView(R.id.car_announcement_year) TextView year;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
         @SuppressLint("SetTextI18n")
         public void setData(String ex, String brandName, String modelName, int Walk, int Price, String Year){
            exChange.setText(ex);
            model.setText(modelName);
            brand.setText(brandName);
            price.setText(Integer.toString(Price));
            walk.setText("Yuruyus " + Walk + "km");
            year.setText("il " + Year);
         }
         public void setImage(String imageUrl){
            if(imageUrl!=null){
                imageView.setImageBitmap(Convert64.convertBase64Image(imageUrl));
            }else{
                imageView.setImageResource(R.drawable.happy);
            }
         }
    }
}
