package com.fyp.equiz;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class CreateQuiz extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton obj,sub;
    CardView subjective,objective;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_questions);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        obj=(RadioButton) findViewById(R.id.checkObj);
        sub=(RadioButton) findViewById(R.id.checkSub);
        subjective=(CardView) findViewById(R.id.cardviewSub);
        objective=(CardView) findViewById(R.id.cardviewMcq);

        subjective.setVisibility(View.INVISIBLE);
        objective.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.checkObj) {

                    subjective.setVisibility(View.INVISIBLE);
                    objective.setVisibility(View.VISIBLE);

                } else if (i == R.id.checkSub) {

                    subjective.setVisibility(View.VISIBLE);
                    objective.setVisibility(View.INVISIBLE);

                }
            }
        });




    }

}
