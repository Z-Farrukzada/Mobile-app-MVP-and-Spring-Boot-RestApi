package com.zaurfarrukhzada.carannouncementmobileproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class City {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String Name;

    public City(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
