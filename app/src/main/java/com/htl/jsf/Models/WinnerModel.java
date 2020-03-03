package com.htl.jsf.Models;

public class WinnerModel {

    private String winner_coupon_code;
    private String winner_name;
    private String winner_date;
    private String winner_time;
    private String winner_position;

    public WinnerModel(String winner_coupon_code, String winner_name, String winner_date, String winner_time, String winner_position) {
        this.winner_coupon_code = winner_coupon_code;
        this.winner_name = winner_name;
        this.winner_date = winner_date;
        this.winner_time = winner_time;
        this.winner_position = winner_position;
    }

    public String getWinner_coupon_code() {
        return winner_coupon_code;
    }

    public void setWinner_coupon_code(String winner_coupon_code) {
        this.winner_coupon_code = winner_coupon_code;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getWinner_date() {
        return winner_date;
    }

    public void setWinner_date(String winner_date) {
        this.winner_date = winner_date;
    }

    public String getWinner_time() {
        return winner_time;
    }

    public void setWinner_time(String winner_time) {
        this.winner_time = winner_time;
    }

    public String getWinner_position() {
        return winner_position;
    }

    public void setWinner_position(String winner_position) {
        this.winner_position = winner_position;
    }
}
