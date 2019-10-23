package com.fyp.quickrepairworkshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.quickrepairworkshop.DataModel.WorkshopDataModel;
import com.fyp.quickrepairworkshop.RecyclerAdaptor.WorkshopList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Services extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private RelativeLayout anim;
    private LottieAnimationView searchanim;


    RecyclerView recyclerView;
    WorkshopList Adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Services.this, PostShop.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.workshopRecycler);
        Adaptor = new WorkshopList(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adaptor);
        anim=(RelativeLayout) findViewById(R.id.anim);
        searchanim=(LottieAnimationView) findViewById(R.id.search);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Post");
        recyclerView.setAdapter(Adaptor);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                searchanim.setVisibility(View.GONE);
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    WorkshopDataModel shopDetails = dataSnapshot1.getValue(WorkshopDataModel.class);
                    Adaptor.addMessage(shopDetails);


                }
                if(Adaptor.getItemCount()==0)
                {
                    anim.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Services.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
