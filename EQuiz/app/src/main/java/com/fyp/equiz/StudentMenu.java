package com.fyp.equiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StudentMenu extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragmentSelected=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentSelected=new FragmentHome();
                    break;
                case R.id.navigation_test:
                    fragmentSelected=new FragmentTest();
                    break;
                case R.id.navigation_courses:
                    fragmentSelected=new FragmentCourse();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragmentSelected).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        if (Build.VERSION.SDK_INT >= 21) {// Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titleGreen)); //status bar or the time bar at the top
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new FragmentHome()).commit();

        /*CardView test=(CardView) findViewById(R.id.TestOpenview);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMenu.this, TextView.class));
            }
        });*/
    }

}
