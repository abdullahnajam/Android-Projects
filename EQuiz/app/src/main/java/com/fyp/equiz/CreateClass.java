package com.fyp.equiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyp.equiz.Model.ClassData;
import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.Model.TeacherData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CreateClass extends AppCompatActivity {

    EditText editClassName, editClassID, editClassKey;
    TextView done;
    RelativeLayout createClassLayout;

    FirebaseAuth tAuth;
    FirebaseDatabase db;
    DatabaseReference classRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }

        tAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        classRef = db.getReference().child(DatabaseTable.getClasses_table());

        editClassName = (EditText) findViewById(R.id.className);
        editClassID = (EditText) findViewById(R.id.classID);
        editClassKey = (EditText) findViewById(R.id.classKey);
        done = (TextView) findViewById(R.id.done);
        createClassLayout = (RelativeLayout) findViewById(R.id.createClassLayout);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createClass();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            startActivity(new Intent(CreateClass.this, CourseInfo.class));
        }
        return super.onKeyDown(keyCode, event);
    }

    private void createClass() {
        //check validation
        if(TextUtils.isEmpty(editClassName.getText().toString()))
        {
            Snackbar.make(createClassLayout,"Please Enter Class Name",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editClassID.getText().toString()))
        {
            Snackbar.make(createClassLayout,"Please Enter Class ID",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editClassKey.getText().toString()))
        {
            Snackbar.make(createClassLayout,"Please Enter Class Key",Snackbar.LENGTH_SHORT).show();
            return;
        }

        //save class to db
        ClassData class_data = new ClassData();
        class_data.setClassID(editClassID.getText().toString());
        class_data.setClassName(editClassName.getText().toString());
        class_data.setEnrollmentKey(editClassKey.getText().toString());

        classRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .push()
                .setValue(class_data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(createClassLayout,"Class Created Successfully",Snackbar.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(createClassLayout,"Class Creation Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
}
