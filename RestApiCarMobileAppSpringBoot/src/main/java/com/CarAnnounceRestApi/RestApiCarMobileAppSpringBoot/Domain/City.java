package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain;

public class City {

    private int id;
    private String Name;


    public City(String name) {
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
