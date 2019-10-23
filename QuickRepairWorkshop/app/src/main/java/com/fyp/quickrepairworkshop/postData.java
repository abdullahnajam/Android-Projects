package com.fyp.quickrepairworkshop;

public class postData {
    public String description,address,away;
    public String rating,category;
    public String username,userphone;
    public LocationTracker coordinates;
    public long time;

    public LocationTracker getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LocationTracker coordinates) {
        this.coordinates = coordinates;
    }

    public postData(String description, String address, String rating, String away, String username, String userphone, String category,Long time) {
        this.description = description;
        this.address = address;
        this.away = away;
        this.rating = rating;
        this.username=username;
        this.userphone=userphone;
        this.category=category;
        this.time=time;
    }
}
