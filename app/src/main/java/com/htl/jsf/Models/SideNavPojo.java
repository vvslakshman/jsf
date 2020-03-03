package com.htl.jsf.Models;

public class SideNavPojo {

    private int logo;
    private String title;

    public int getLogo() {
        return logo;
    }

    public SideNavPojo(int logo, String title) {
        this.logo = logo;
        this.title = title;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
