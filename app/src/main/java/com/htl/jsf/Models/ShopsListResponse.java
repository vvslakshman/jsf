package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopsListResponse {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ShapsDataResponse> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShapsDataResponse> getData() {
        return data;
    }

    public void setData(List<ShapsDataResponse> data) {
        this.data = data;
    }

    public  class ShapsDataResponse {
        @SerializedName("shop_id")
        @Expose
        private String shopId;
        @SerializedName("shop_name")
        @Expose
        private String shopName;
        @SerializedName("shop_mobile")
        @Expose
        private String shopMobile;
        @SerializedName("shop_owner")
        @Expose
        private String shopOwner;
        @SerializedName("shop_owner_mobile")
        @Expose
        private String shopOwnerMobile;
        @SerializedName("shop_address")
        @Expose
        private String shopAddress;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("shop_type")
        @Expose
        private String shopType;
        @SerializedName("shop_image")
        @Expose
        private String shopImage;
        @SerializedName("shop_info")
        @Expose
        private String shopInfo;


        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopMobile() {
            return shopMobile;
        }

        public void setShopMobile(String shopMobile) {
            this.shopMobile = shopMobile;
        }

        public String getShopOwner() {
            return shopOwner;
        }

        public void setShopOwner(String shopOwner) {
            this.shopOwner = shopOwner;
        }

        public String getShopOwnerMobile() {
            return shopOwnerMobile;
        }

        public void setShopOwnerMobile(String shopOwnerMobile) {
            this.shopOwnerMobile = shopOwnerMobile;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getShopImage() {
            return shopImage;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public String getShopInfo() {
            return shopInfo;
        }

        public void setShopInfo(String shopInfo) {
            this.shopInfo = shopInfo;
        }

    }
}
