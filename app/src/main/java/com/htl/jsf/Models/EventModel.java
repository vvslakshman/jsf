package com.htl.jsf.Models;

public class EventModel {
    private String music_night;
    private String music_night_location;
    private String artist_name;
    private String music_night_time;
    private String music_night_contact;
    private String music_night_about;
    private String music_night_with;

    public EventModel(String music_night, String music_night_location, String artist_name, String music_night_time, String music_night_contact, String music_night_about, String music_night_with) {
        this.music_night = music_night;
        this.music_night_location = music_night_location;
        this.artist_name = artist_name;
        this.music_night_time = music_night_time;
        this.music_night_contact = music_night_contact;
        this.music_night_about = music_night_about;
        this.music_night_with = music_night_with;
    }

    public String getMusic_night() {
        return music_night;
    }

    public void setMusic_night(String music_night) {
        this.music_night = music_night;
    }

    public String getMusic_night_location() {
        return music_night_location;
    }

    public void setMusic_night_location(String music_night_location) {
        this.music_night_location = music_night_location;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getMusic_night_time() {
        return music_night_time;
    }

    public void setMusic_night_time(String music_night_time) {
        this.music_night_time = music_night_time;
    }

    public String getMusic_night_contact() {
        return music_night_contact;
    }

    public void setMusic_night_contact(String music_night_contact) {
        this.music_night_contact = music_night_contact;
    }

    public String getMusic_night_about() {
        return music_night_about;
    }

    public void setMusic_night_about(String music_night_about) {
        this.music_night_about = music_night_about;
    }

    public String getMusic_night_with() {
        return music_night_with;
    }

    public void setMusic_night_with(String music_night_with) {
        this.music_night_with = music_night_with;
    }
}
