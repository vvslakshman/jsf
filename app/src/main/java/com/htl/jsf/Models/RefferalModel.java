package com.htl.jsf.Models;

public class RefferalModel {
    private String Reffer_code;
    private String date;
    private String time;

    public RefferalModel(String reffer_code, String date, String time) {
        Reffer_code = reffer_code;
        this.date = date;
        this.time = time;
    }

    public String getReffer_code() {
        return Reffer_code;
    }

    public void setReffer_code(String reffer_code) {
        Reffer_code = reffer_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
