package com.fyp.equiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestView extends AppCompatActivity {
    TextView done, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titleGreen)); //status bar or the time bar at the top
        }

        done=(TextView) findViewById(R.id.done);
        next=(TextView) findViewById(R.id.next);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(TestView.this, StudentMenu.class));
            }
        });
        final RadioButton first=(RadioButton) findViewById(R.id.first);
        final RadioButton second=(RadioButton) findViewById(R.id.second);
        final RadioButton third=(RadioButton) findViewById(R.id.third);
        final RadioButton forth=(RadioButton) findViewById(R.id.forth);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(false);
                forth.setChecked(false);

            }
        });

    }
}
