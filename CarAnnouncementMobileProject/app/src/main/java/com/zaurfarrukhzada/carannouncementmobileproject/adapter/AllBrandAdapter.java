package com.zaurfarrukhzada.carannouncementmobileproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.utils.Convert64;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllBrandAdapter extends RecyclerView.Adapter<AllBrandAdapter.ViewHolder> implements Filterable {


    List<AllCarBrand> allCarBrandList;
    List<AllCarBrand> allList;
    Activity activity;

    public AllBrandAdapter(List<AllCarBrand> allCarBrandList, Activity activity) {
        this.allCarBrandList = allCarBrandList;
        this.allList = new ArrayList<>(allCarBrandList);
        this.activity = activity;
    }

    @NonNull
    @Override
    public AllBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_brand_items,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBrandAdapter.ViewHolder holder, int position) {
           holder.setName(allCarBrandList.get(position).getName());
           holder.setImage(allCarBrandList.get(position).getLogoImage());
    }

    @Override
    public int getItemCount() {
        return allCarBrandList.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        //Todo:Run a background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<AllCarBrand> filterList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                 filterList.addAll(allList);
            }else{
                for(AllCarBrand brand:allList){
                    if(brand.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                      filterList.add(brand);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        //Todo:Run a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
             allCarBrandList.clear();
             allCarBrandList.addAll((Collection<? extends AllCarBrand>) results.values);
             notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.all_brand_image) ImageView allBrandImage;
        @BindView(R.id.all_brands_car_name) TextView allBrandItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setName(String name){
            allBrandItemName.setText(name);
        }

        public void setImage(String imageUrl) {
            if(imageUrl != null){
                allBrandImage.setImageBitmap(Convert64.convertBase64Image(imageUrl));
            }else{
                allBrandImage.setImageResource(R.drawable.happy);
            }
        }
    }
}
