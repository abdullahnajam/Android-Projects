package com.fyp.quickrepairworkshop;

import android.location.Location;

public class LocationTracker {

    private double longitude,latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LocationTracker(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
