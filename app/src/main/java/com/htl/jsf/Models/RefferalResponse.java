package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RefferalResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<RefferalData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RefferalData> getData() {
        return data;
    }

    public void setData(List<RefferalData> data) {
        this.data = data;
    }

    public static class RefferalData {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("serial_no")
        @Expose
        private String serialNo;
        @SerializedName("coupon_code")
        @Expose
        private String couponCode;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("shop")
        @Expose
        private String shop;
        @SerializedName("datetime")
        @Expose
        private String datetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }
}
