package com.zaurfarrukhzada.carannouncementmobileproject.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.Convert64;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;


public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {


     List<CarBrand> carBrandList;
     Activity activity;

    public BrandAdapter(List<CarBrand> carBrandList, Activity activity) {
        this.carBrandList = carBrandList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item,null,false);
        return new ViewHolder(view);
    }

    @SneakyThrows
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = carBrandList.get(position).getName();
        String imageUrl = carBrandList.get(position).getLogoImage();

        holder.setName(name);
        holder.setImage(imageUrl);

    }

    @Override
    public int getItemCount() {
        return carBrandList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.brand_item_name) TextView brandName;
        @BindView(R.id.brand_logo_image) ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        private void setName(String name){
            brandName.setText(name);
        }

        public void setImage(String imageUrl) {
            if(imageUrl != null){
                imageView.setImageBitmap(Convert64.convertBase64Image(imageUrl));
            }else{
                imageView.setImageResource(R.drawable.happy);
            }
        }
    }
}
