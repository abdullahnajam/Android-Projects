package com.fyp.quickrepair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.quickrepair.Model.AppointmentModel;
import com.fyp.quickrepair.Model.ServiceDataModel;
import com.fyp.quickrepair.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class ShopDetail extends AppCompatActivity implements View.OnClickListener, LocationListener {

    TextView name,Location;
    Intent intent;
    ServiceDataModel post;
    String id;
    private static  final int REQUEST_LOCATION=1;
    LocationManager locationManager;
    TextView locationText;
    String latitude,longitude,addressline,city;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    Button sms,call,email,makeAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


        name=(TextView) findViewById(R.id.shopName);
        Location=(TextView) findViewById(R.id.shopAddress);

        sms=(Button) findViewById(R.id.sms);
        call=(Button) findViewById(R.id.call);
        makeAppointment=(Button) findViewById(R.id.setAppointment);
        email=(Button) findViewById(R.id.email);

        id=getIntent().getStringExtra("id");
        Toast.makeText(ShopDetail.this,id,Toast.LENGTH_LONG).show();
        post=(ServiceDataModel) getIntent().getParcelableExtra("Detail");
        name.setText(post.getDescription().toString());
        Location.setText(post.getAddress().toString());

        getLocation();

        sms.setOnClickListener(this);
        email.setOnClickListener(this);
        call.setOnClickListener(this);
        makeAppointment.setOnClickListener(this);

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
    void call ()
    {
        String phone=post.getUserphone().trim();

        if(phone.isEmpty())
        {
            Toast.makeText(ShopDetail.this,"Cannot load Phone Number",Toast.LENGTH_LONG).show();


        }
        else {
            long tel=Long.parseLong(phone);
            intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+92"+tel));
            Toast.makeText(ShopDetail.this,post.getUserphone(),Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }
    void sms ()
    {
        String phone=post.getUserphone().trim();

        if(phone.isEmpty())
        {
            Toast.makeText(ShopDetail.this,"Cannot load Phone Number",Toast.LENGTH_LONG).show();


        }
        else {
            long tel=Long.parseLong(phone);
            /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", tel);
            smsIntent.putExtra("sms_body","Body of Message");
            startActivity(smsIntent);*/
            //sendSMS(tel);
            startSMSIntent(phone,"");
        }
    }

    public void startSMSIntent(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        // This ensures only SMS apps respond
        intent.setData(Uri.parse("smsto:"+phoneNumber));
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.call:
                //startActivity(new Intent(MainMenu.this, MapsActivity.class));
                call();
                //Toast.makeText(MainMenu .this,"Pressed",Toast.LENGTH_LONG).show();
                break;

            case R.id.sms:
                sms();
                break;

            case R.id.email:
                Toast.makeText(ShopDetail.this,"Under maintenance",Toast.LENGTH_LONG).show();
                break;

            case R.id.setAppointment:
                requestAppointment();
                break;
        }

    }

    private void requestAppointment() {
        final long now = System.currentTimeMillis();
        final DatabaseReference datausers,userdata;
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userdata=FirebaseDatabase.getInstance().getReference().child("UserData");
        datausers = FirebaseDatabase.getInstance().getReference().child("Post").child(id).child("Appointment");
        userdata.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users userdetails = dataSnapshot.getValue(Users.class);
                AppointmentModel post= new AppointmentModel(userdetails.getUsername(),user.getEmail(),userdetails.getPhone(),now,now,addressline,latitude,longitude);
                datausers.push().setValue(post,user.getUid());
                Toast.makeText(ShopDetail.this,"Appointment Request",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        latitude=location.getLatitude()+"";
        longitude=location.getLongitude()+"";
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            addressline=addresses.get(0).getAddressLine(0);

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
        Toast.makeText(ShopDetail.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }
}
