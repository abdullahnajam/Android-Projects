package com.fyp.quickrepair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.quickrepair.Adaptor.ServicesAdaptor;
import com.fyp.quickrepair.Model.ServiceDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServicesListView extends AppCompatActivity implements LocationListener {


    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private LottieAnimationView anim;
    ArrayList<String> keys=new ArrayList<>();
    ArrayList<Float> away=new ArrayList<>();
    LocationManager locationManager;
    TextView locationText;
    String latitude,longitude,addressline,city;
    private Location userClocation,userPlocation;

    RecyclerView recyclerView;
    ServicesAdaptor Adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list_view);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        recyclerView = (RecyclerView) findViewById(R.id.serviceRecycler);
        Adaptor = new ServicesAdaptor(this,keys,away);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adaptor);
        anim=(LottieAnimationView) findViewById(R.id.animation);
        locationText=(TextView) findViewById(R.id.locationText);
        getLocation();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Post");
        recyclerView.setAdapter(Adaptor);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anim.setVisibility(View.GONE);

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren())
                    {
                        keys.add(dataSnapshot2.getKey());
                        ServiceDataModel userdetails = dataSnapshot2.getValue(ServiceDataModel.class);
                        //calculating distance
                        userPlocation = new Location("");
                        userPlocation.setLatitude(Double.parseDouble(userdetails.getLattidute()));
                        userPlocation.setLongitude(Double.parseDouble(userdetails.getLongitude()));
                        if(userClocation==null)
                        {
                            if (ActivityCompat.checkSelfPermission(ServicesListView.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ServicesListView.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            userClocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                        /*userClocation.setLatitude(33.6111999f);
                        userClocation.setLongitude(72.9872115f);*/
                        }
                        if(userClocation!=null)
                        {
                            float distanceInMeters = userClocation.distanceTo(userPlocation);
                            distanceInMeters/=1000;
                            DecimalFormat numberFormat = new DecimalFormat("#.0");

                            away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));

                            Adaptor.addMessage(userdetails);

                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ServicesListView.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude()+"";
        longitude=location.getLongitude()+"";
        userClocation=location;
        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            addresses.get(0).getAddressLine(0);
            //address.setText(addresses.get(0).getAddressLine(0));
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ServicesListView.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }
}
