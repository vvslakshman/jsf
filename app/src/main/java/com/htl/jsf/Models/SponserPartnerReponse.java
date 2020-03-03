package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponserPartnerReponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SponserData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SponserData> getData() {
        return data;
    }

    public void setData(List<SponserData> data) {
        this.data = data;
    }

    public static class SponserData {
        @SerializedName("sponser_id")
        @Expose
        private String sponserId;
        @SerializedName("sponser_name")
        @Expose
        private String sponserName;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("sponser_type")
        @Expose
        private String sponserType;
        @SerializedName("sponser_company")
        @Expose
        private String sponserCompany;
        @SerializedName("sponser_website")
        @Expose
        private String sponserWebsite;
        @SerializedName("sponser_location")
        @Expose
        private String sponserLocation;
        @SerializedName("sponser_area")
        @Expose
        private String sponserArea;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("sponser_area_zip")
        @Expose
        private String sponserAreaZip;
        @SerializedName("sponser_mobile")
        @Expose
        private String sponserMobile;
        @SerializedName("sponser_landline1")
        @Expose
        private String sponserLandline1;
        @SerializedName("sponser_landline2")
        @Expose
        private String sponserLandline2;
        @SerializedName("sponser_about")
        @Expose
        private String sponserAbout;
        @SerializedName("sponser_offer")
        @Expose
        private String sponserOffer;
        @SerializedName("sponser_offer_image")
        @Expose
        private String sponserOfferImage;

        public String getSponserId() {
            return sponserId;
        }

        public void setSponserId(String sponserId) {
            this.sponserId = sponserId;
        }

        public String getSponserName() {
            return sponserName;
        }

        public void setSponserName(String sponserName) {
            this.sponserName = sponserName;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSponserType() {
            return sponserType;
        }

        public void setSponserType(String sponserType) {
            this.sponserType = sponserType;
        }

        public String getSponserCompany() {
            return sponserCompany;
        }

        public void setSponserCompany(String sponserCompany) {
            this.sponserCompany = sponserCompany;
        }

        public String getSponserWebsite() {
            return sponserWebsite;
        }

        public void setSponserWebsite(String sponserWebsite) {
            this.sponserWebsite = sponserWebsite;
        }

        public String getSponserLocation() {
            return sponserLocation;
        }

        public void setSponserLocation(String sponserLocation) {
            this.sponserLocation = sponserLocation;
        }

        public String getSponserArea() {
            return sponserArea;
        }

        public void setSponserArea(String sponserArea) {
            this.sponserArea = sponserArea;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSponserAreaZip() {
            return sponserAreaZip;
        }

        public void setSponserAreaZip(String sponserAreaZip) {
            this.sponserAreaZip = sponserAreaZip;
        }

        public String getSponserMobile() {
            return sponserMobile;
        }

        public void setSponserMobile(String sponserMobile) {
            this.sponserMobile = sponserMobile;
        }

        public String getSponserLandline1() {
            return sponserLandline1;
        }

        public void setSponserLandline1(String sponserLandline1) {
            this.sponserLandline1 = sponserLandline1;
        }

        public String getSponserLandline2() {
            return sponserLandline2;
        }

        public void setSponserLandline2(String sponserLandline2) {
            this.sponserLandline2 = sponserLandline2;
        }

        public String getSponserAbout() {
            return sponserAbout;
        }

        public void setSponserAbout(String sponserAbout) {
            this.sponserAbout = sponserAbout;
        }

        public String getSponserOffer() {
            return sponserOffer;
        }

        public void setSponserOffer(String sponserOffer) {
            this.sponserOffer = sponserOffer;
        }

        public String getSponserOfferImage() {
            return sponserOfferImage;
        }

        public void setSponserOfferImage(String sponserOfferImage) {
            this.sponserOfferImage = sponserOfferImage;
        }
    }
}
