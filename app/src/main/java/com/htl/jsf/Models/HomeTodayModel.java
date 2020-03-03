package com.htl.jsf.Models;


public class HomeTodayModel {
    private String winnername;
    private String winnerlocation;
    private String winnerprize;

    public HomeTodayModel(String winnername, String winnerlocation, String winnerprize) {
        this.winnername = winnername;
        this.winnerlocation = winnerlocation;
        this.winnerprize = winnerprize;
    }

    public String getWinnername() {
        return winnername;
    }

    public void setWinnername(String winnername) {
        this.winnername = winnername;
    }

    public String getWinnerlocation() {
        return winnerlocation;
    }

    public void setWinnerlocation(String winnerlocation) {
        this.winnerlocation = winnerlocation;
    }

    public String getWinnerprize() {
        return winnerprize;
    }

    public void setWinnerprize(String winnerprize) {
        this.winnerprize = winnerprize;
    }
}
