package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;


import java.util.Locale;

public class User {

    private int Id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private int city_id;

    public User(int Id, String username, String email, String password, String phone, int city_id) {
         this.Id = Id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city_id = city_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username.toLowerCase();
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
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

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
