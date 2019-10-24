package com.fyp.equiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.equiz.Model.ClassData;
import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.RecyclerAdapter.ClassList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CourseInfo extends AppCompatActivity {

    FloatingActionButton fabCreateClass;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private RelativeLayout anim,emptystate;

    RecyclerView recyclerView;
    ClassList Adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }

        fabCreateClass = findViewById(R.id.fabCreateClass);

        fabCreateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseInfo.this, CreateClass.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.workshopRecycler);
        Adaptor = new ClassList(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adaptor);
        emptystate=(RelativeLayout) findViewById(R.id.emptystate);
        //searchanim=(LottieAnimationView) findViewById(R.id.search);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference().child(DatabaseTable.getClasses_table()).child(FirebaseAuth.getInstance().getUid());
        recyclerView.setAdapter(Adaptor);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //searchanim.setVisibility(View.GONE);
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Log.d("testing", dataSnapshot1.child("className").getValue().toString());
                    ClassData classDetails = dataSnapshot1.getValue(ClassData.class);
                    Adaptor.addMessage(classDetails);


                }
                if(Adaptor.getItemCount()>0)
                {
                    emptystate.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(CourseInfo.this, "Failed to load class.",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            //finish();
            startActivity(new Intent(CourseInfo.this, TeacherMainMenu.class));
        }
        return super.onKeyDown(keyCode, event);
    }
}

