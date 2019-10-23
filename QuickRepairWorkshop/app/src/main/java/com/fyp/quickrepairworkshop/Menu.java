package com.fyp.quickrepairworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    CardView booking,services,logout,request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //inflating views
        booking=(CardView) findViewById(R.id.bookingCardView);
        services=(CardView) findViewById(R.id.serviceCardView);
        request=(CardView) findViewById(R.id.requestCardView);
        logout=(CardView) findViewById(R.id.logoutcardview);

        //setting the status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkbleue)); //status bar or the time bar at the top
        }

        booking.setOnClickListener(this);
        request.setOnClickListener(this);
        services.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bookingCardView:
                startActivity(new Intent(Menu.this, ViewBooking.class));
                break;

            case R.id.requestCardView:
                startActivity(new Intent(Menu.this, RequestList.class));
                break;

            case R.id.serviceCardView:
                startActivity(new Intent(Menu.this, Services.class));
                break;

            case R.id.logoutcardview:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Menu.this, Login.class));
                break;
        }
    }
}
