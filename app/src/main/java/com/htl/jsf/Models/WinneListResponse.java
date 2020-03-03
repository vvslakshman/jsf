package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WinneListResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<WinnerData> data = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WinnerData> getData() {
        return data;
    }

    public void setData(List<WinnerData> data) {
        this.data = data;
    }

    public class WinnerData{


        /*add new */
        @SerializedName("coupon_code")
        @Expose
        String coupon_code;

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("shop")
        @Expose
        private String shop;

        @SerializedName("coupon_code_series")
        @Expose
        private String coupon_code_series;

        @SerializedName("position")
        @Expose
        private String position;


        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("time")
        @Expose
        private String time;



        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public String getCoupon_code_series() {
            return coupon_code_series;
        }

        public void setCoupon_code_series(String coupon_code_series) {
            this.coupon_code_series = coupon_code_series;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }





}
