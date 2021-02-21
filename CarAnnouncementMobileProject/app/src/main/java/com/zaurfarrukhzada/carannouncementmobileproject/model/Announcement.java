package com.zaurfarrukhzada.carannouncementmobileproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("exchange_name")
    @Expose
    private String exchangeName;
    @SerializedName("model_name")
    @Expose
    private String modelName;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("walk")
    @Expose
    private Integer walk;
    @SerializedName("carYear")
    @Expose
    private String carYear;
}
