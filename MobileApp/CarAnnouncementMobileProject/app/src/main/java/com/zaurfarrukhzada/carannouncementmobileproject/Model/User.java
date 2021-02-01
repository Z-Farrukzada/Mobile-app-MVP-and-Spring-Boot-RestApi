package com.zaurfarrukhzada.carannouncementmobileproject.model;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @SerializedName("Id")
    @Expose
    private int Id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city_id")
    @Expose
    private long city_id;


    public boolean isValid(){

        return false;


    }

}
