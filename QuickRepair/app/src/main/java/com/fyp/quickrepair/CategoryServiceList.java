package com.fyp.quickrepair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.fyp.quickrepair.Adaptor.ServicesAdaptor;
import com.fyp.quickrepair.Model.ServiceDataModel;
import com.fyp.quickrepair.Model.UserLocation;
import com.fyp.quickrepair.Model.Values;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CategoryServiceList extends AppCompatActivity {
    DatabaseReference datausers;
    Intent intent;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private LottieAnimationView anim;
    ArrayList<String> keys=new ArrayList<>();
    ArrayList<Float> away=new ArrayList<>();
    RecyclerView recyclerView;
    ServicesAdaptor Adaptor;
    Location userPlocation;
    Boolean removekey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list_view);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }


        inflateViews();
        setUpRecyclerView();
        Location location=getUserCurrentLocation();
        datafetch(location);





    }
    public void datafetch(final Location userClocation)
    {
        final Values values=new Values();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Post");
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
                        userPlocation.setLatitude(33.6111999f);
                        userPlocation.setLongitude(72.9872115f);
                        if(userClocation!=null)
                        {
                            float distanceInMeters = userClocation.distanceTo(userPlocation);
                            distanceInMeters/=1000;
                            DecimalFormat numberFormat = new DecimalFormat("#.0");

                            if(getIntentCategory()==values.getRENT_CATEGORY())
                            {
                                if(userdetails.getCategory().contains("Rent A Car") && distanceInMeters<=10000f)
                                {
                                    away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));
                                    Adaptor.addMessage(userdetails);
                                }
                            }
                            else if(getIntentCategory()==values.getCARWASH_CATEGORY())
                            {
                                if(userdetails.getCategory().contains("Car Wash") && distanceInMeters<=10000f)
                                {
                                    away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));
                                    Adaptor.addMessage(userdetails);
                                }
                            }
                            else if(getIntentCategory().contains(values.getMECHANIC_CATEGORY()))
                            {
                                if(userdetails.getCategory().contains("Mechanic") && distanceInMeters<=10000f)
                                {
                                    away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));
                                    Adaptor.addMessage(userdetails);
                                }
                            }
                            else if(getIntentCategory()==values.getOIL_CATEGORY())
                            {
                                if(userdetails.getCategory().contains("Oil Change") && distanceInMeters<=10000f)
                                {
                                    away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));
                                    Adaptor.addMessage(userdetails);
                                }

                            }
                            else if(getIntentCategory()==values.getOTHER_CATEGORY())
                            {
                                if(userdetails.getCategory().contains("Others") && distanceInMeters<=10000f)
                                {
                                    away.add(Float.parseFloat(numberFormat.format(distanceInMeters)));
                                    Adaptor.addMessage(userdetails);
                                }

                            }
                            /*if(!removekey)
                            {
                                keys.remove(keys.size()-1);
                            }
                            else
                                removekey=true;
*/




                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(CategoryServiceList.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setUpRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.serviceRecycler);
        Adaptor = new ServicesAdaptor(this,keys,away);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adaptor);
    }
    public void inflateViews()
    {
        anim=(LottieAnimationView) findViewById(R.id.animation);

    }

    public Location getUserCurrentLocation()
    {
        UserLocation customerCurrentLocation=new UserLocation(getApplicationContext(), CategoryServiceList.this);
        return customerCurrentLocation.getCurrentLocation();
    }
    public String getIntentCategory()
    {
        Values values=new Values();
        String category=getIntent().getStringExtra(values.getSEARCH_CATEGORY_TAG());
        return category;
    }
}
