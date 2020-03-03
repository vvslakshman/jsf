package com.htl.jsf.Models;

public class MarketModel {
    private String shopname;
    private String ownername;
    private String shopnumber;
    private String shoplocation;

    public MarketModel(String shopname, String ownername, String shopnumber, String shoplocation) {
        this.shopname = shopname;
        this.ownername = ownername;
        this.shopnumber = shopnumber;
        this.shoplocation = shoplocation;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getShopnumber() {

        return shopnumber;
    }

    public void setShopnumber(String shopnumber) {
        this.shopnumber = shopnumber;
    }

    public String getShoplocation() {
        return shoplocation;
    }

    public void setShoplocation(String shoplocation) {
        this.shoplocation = shoplocation;
    }
}
