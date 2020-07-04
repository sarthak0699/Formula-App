package com.example.formulaproject;

public class RaceItem {
    private String Location,Date,Time;
    int imageId;

    public RaceItem(String location, String date, String time, int imageId) {
        Location = location;
        Date = date;
        Time = time;
        this.imageId = imageId;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
