package com.zaurfarrukhzada.carannouncementmobileproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SliderItem {

    @SerializedName("id")
    @Expose
    private  int id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
}
