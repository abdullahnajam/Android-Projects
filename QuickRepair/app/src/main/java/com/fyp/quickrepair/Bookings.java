package com.fyp.quickrepair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;

public class Bookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }
    }
}
