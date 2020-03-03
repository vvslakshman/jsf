package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityModel {
    @Expose
    @SerializedName("data")
    public List<CityDataResponse> data ;
    @Expose
    @SerializedName("status")
    public String status;

    public CityModel(List<CityDataResponse> data, String status) {
        this.data = data;
        this.status = status;
    }

    public List<CityDataResponse> getData() {
        return data;
    }

    public void setData(List<CityDataResponse> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
