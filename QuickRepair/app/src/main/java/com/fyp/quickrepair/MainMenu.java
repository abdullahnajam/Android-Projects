package com.fyp.quickrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(MainMenu.this, Login.class));
    }
}
