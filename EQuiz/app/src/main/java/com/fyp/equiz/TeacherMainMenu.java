package com.fyp.equiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fyp.equiz.Model.Common;

public class TeacherMainMenu extends AppCompatActivity {

    TextView teacherName, teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_menu);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }

        teacherName = (TextView) findViewById(R.id.teacherName);
        teacherEmail = (TextView) findViewById(R.id.teacherEmail);

        teacherName.setText(Common.currentTeacher.getName().toString());
        teacherEmail.setText(Common.currentTeacher.getEmail().toString());

        CardView Logout=(CardView) findViewById(R.id.logoutcardview);
        CardView test=(CardView) findViewById(R.id.testcardview);
        CardView course=(CardView) findViewById(R.id.cardview);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherMainMenu.this, MainActivity.class));
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherMainMenu.this, teacher_quizes.class));
            }
        });
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherMainMenu.this, CourseInfo.class));
            }
        });

    }
}
