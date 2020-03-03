package com.htl.jsf.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodayEventResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<TodayEventData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TodayEventData> getData() {
        return data;
    }

    public void setData(List<TodayEventData> data) {
        this.data = data;
    }

    public class TodayEventData {

        @SerializedName("event_id")
        @Expose
        private String eventId;
        @SerializedName("event_name")
        @Expose
        private String eventName;
        @SerializedName("event_performer")
        @Expose
        private String eventPerformer;
        @SerializedName("event_manager")
        @Expose
        private String eventManager;
        @SerializedName("event_manage_mobile")
        @Expose
        private String eventManageMobile;
        @SerializedName("event_place")
        @Expose
        private String eventPlace;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("event_image")
        @Expose
        private String eventImage;
        @SerializedName("event_date")
        @Expose
        private String eventDate;
        @SerializedName("event_time")
        @Expose
        private String eventTime;


        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getEventPerformer() {
            return eventPerformer;
        }

        public void setEventPerformer(String eventPerformer) {
            this.eventPerformer = eventPerformer;
        }

        public String getEventManager() {
            return eventManager;
        }

        public void setEventManager(String eventManager) {
            this.eventManager = eventManager;
        }

        public String getEventManageMobile() {
            return eventManageMobile;
        }

        public void setEventManageMobile(String eventManageMobile) {
            this.eventManageMobile = eventManageMobile;
        }

        public String getEventPlace() {
            return eventPlace;
        }

        public void setEventPlace(String eventPlace) {
            this.eventPlace = eventPlace;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEventImage() {
            return eventImage;
        }

        public void setEventImage(String eventImage) {
            this.eventImage = eventImage;
        }

        public String getEventDate() {
            return eventDate;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        public String getEventTime() {
            return eventTime;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }
    }



}



