package com.htl.jsf.Models;

public class SponsorModel {
    private String sponsorname;
    private String sponsorcompany;
    private String sponsormobile;
    private String sponsorcity;

    public SponsorModel(String sponsorname, String sponsorcompany, String sponsormobile, String sponsorcity) {
        this.sponsorname = sponsorname;
        this.sponsorcompany = sponsorcompany;
        this.sponsormobile = sponsormobile;
        this.sponsorcity = sponsorcity;
    }

    public String getSponsorname() {
        return sponsorname;
    }

    public void setSponsorname(String sponsorname) {
        this.sponsorname = sponsorname;
    }

    public String getSponsorcompany() {
        return sponsorcompany;
    }

    public void setSponsorcompany(String sponsorcompany) {
        this.sponsorcompany = sponsorcompany;
    }

    public String getSponsormobile() {
        return sponsormobile;
    }

    public void setSponsormobile(String sponsormobile) {
        this.sponsormobile = sponsormobile;
    }

    public String getSponsorcity() {
        return sponsorcity;
    }

    public void setSponsorcity(String sponsorcity) {
        this.sponsorcity = sponsorcity;
    }
}
