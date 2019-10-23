package com.fyp.quickrepairworkshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.quickrepairworkshop.DataModel.WorkshopDataModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class PostShop extends AppCompatActivity implements View.OnClickListener, LocationListener {


    DatabaseReference datausers,userdata;
    private FirebaseAuth mAuth;
    private EditText des,charges,address;
    String dis,rating,add,charge,type;
    private Button logout,post;
    private static  final int REQUEST_LOCATION=1;
    LocationManager locationManager;
    TextView locationText;
    String latitude,longitude,addressline,city;/*
    private double latitude,longitude;*/
    Integer postrate=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_shop);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }




        final Spinner spinnerDropDownView;
        String[] spinnerValueHoldValue = {"Rent A Car", "Mechanic","Car Wash", "Oil Change", "Others"};
        spinnerDropDownView =(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PostShop.this, android.R.layout.simple_list_item_1, spinnerValueHoldValue);
        spinnerDropDownView.setAdapter(adapter);

        locationText=findViewById(R.id.show_location);

        spinnerDropDownView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type=spinnerDropDownView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        des = (EditText) findViewById(R.id.dis);
        address = (EditText) findViewById(R.id.address);
        charges = (EditText) findViewById(R.id.charges);


        logout= (Button) findViewById(R.id.logout);
        post= (Button) findViewById(R.id.post);

        mAuth = FirebaseAuth.getInstance();


        getLocation();



        post.setOnClickListener(this);
        logout.setOnClickListener(this);

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

    void post ()
    {

        final long now = System.currentTimeMillis();
        dis=des.getText().toString();
        add=address.getText().toString();
        charge=charges.getText().toString();
//        rating=rate.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userdata=FirebaseDatabase.getInstance().getReference().child("UserData");
        datausers = FirebaseDatabase.getInstance().getReference().child("Post");
        userdata.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users userdetails = dataSnapshot.getValue(Users.class);
                WorkshopDataModel post= new WorkshopDataModel(dis,add,charge,userdetails.getUsername(),userdetails.getPhone(),type,now,latitude,longitude);
                datausers.push().setValue(post);
                Toast.makeText(PostShop.this,"Workshop posted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post:
                //startActivity(new Intent(PostShop.this, MapsActivity.class));
                post();
                Toast.makeText(PostShop.this,latitude+"  "+longitude,Toast.LENGTH_LONG).show();
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(PostShop.this, Login.class));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude()+"";
        longitude=location.getLongitude()+"";
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            addresses.get(0).getAddressLine(0);
            address.setText(addresses.get(0).getAddressLine(0));
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
        Toast.makeText(PostShop.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }

}
