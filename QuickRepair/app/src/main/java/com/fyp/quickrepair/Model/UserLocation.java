package com.fyp.quickrepair.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.fyp.quickrepair.ServicesListView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class UserLocation implements LocationListener {
    LocationManager locationManager;
    Location currentLocation;
    private Context context;
    Activity activity;

    public Location getCurrentLocation() {
        getCurrentLocationListner();
        return currentLocation;
    }

    public UserLocation(Context context, Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    private void getCurrentLocationListner()
    {
        locationPermission();
        getLocation();
        if(currentLocation==null)
        {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            currentLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        }

    }

    private void locationPermission()
    {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }
    private void getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        currentLocation=location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
