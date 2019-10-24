package com.fyp.equiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import customfonts.MyTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyTextView student,teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student=(MyTextView) findViewById(R.id.student);
        teacher=(MyTextView) findViewById(R.id.teacher);

        student.setOnClickListener(this);
        teacher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.student:
                startActivity(new Intent(this, student.class));
                break;

            case R.id.teacher:
                startActivity(new Intent(this, teacher.class));
                break;
        }

    }
}
