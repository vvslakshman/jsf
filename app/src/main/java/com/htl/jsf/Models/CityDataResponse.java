package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityDataResponse {
    @Expose
    @SerializedName("city")
    public String city;
    @Expose
    @SerializedName("city_id")
    public String city_id;

    public CityDataResponse(String city, String city_id) {
        this.city = city;
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
