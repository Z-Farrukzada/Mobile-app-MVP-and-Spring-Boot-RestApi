package com.zaurfarrukhzada.carannouncementmobileproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaurfarrukhzada.carannouncementmobileproject.R;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

     List<CarModel> carModelList;

    public ModelAdapter(List<CarModel> carModelList) {
        this.carModelList = carModelList;
    }

    @NonNull
    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_items,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.ViewHolder holder, int position) {
               holder.setName(carModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return carModelList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.model_car_name)
        TextView modelName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setName(String name){
            modelName.setText(name);
        }
    }
}
