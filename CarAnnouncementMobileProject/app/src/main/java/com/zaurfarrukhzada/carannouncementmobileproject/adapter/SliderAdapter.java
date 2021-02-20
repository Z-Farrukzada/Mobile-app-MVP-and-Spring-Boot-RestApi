package com.zaurfarrukhzada.carannouncementmobileproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.Convert64;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    List<SliderItem> sliderItemList;
    ViewPager2 viewPager2;


    public SliderAdapter(List<SliderItem> sliderItemList, ViewPager2 viewPager2) {
        this.sliderItemList = sliderItemList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_items,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
           holder.setImage(sliderItemList.get(position));
           if(position == sliderItemList.size()-2){
               viewPager2.post(runnable);
           }
    }

    @Override
    public int getItemCount() {
        return sliderItemList.size();
    }


    public class  SliderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_rounded_view_slide)
        RoundedImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setImage(SliderItem sliderItem){
            if(sliderItem.getImageUrl() != null){
                imageView.setImageBitmap(Convert64.convertBase64Image(sliderItem.getImageUrl()));
            }else{
                imageView.setImageResource(R.drawable.happy);
            }

        }
    }
    private  final Runnable runnable = () -> {
        sliderItemList.addAll(sliderItemList);
        notifyDataSetChanged();
    };

}
