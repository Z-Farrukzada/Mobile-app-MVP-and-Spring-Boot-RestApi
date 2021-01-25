package com.zaurfarrukhzada.carannouncementmobileproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



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

    public User(int id, String username, String email, String password, String phone, long city_id) {
        Id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city_id = city_id;
    }
    public User(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }
}
