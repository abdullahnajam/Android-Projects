package com.fyp.equiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.equiz.Model.Common;
import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.Model.StudentData;
import com.fyp.equiz.Model.TeacherData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.security.auth.login.LoginException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class student extends AppCompatActivity {

    EditText editEmail, editPassword;
    TextView register;
    Button btnLogin;
    RelativeLayout studentLoginLayout;

    FirebaseAuth sAuth;
    FirebaseDatabase db;
    DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titleGreen)); //status bar or the time bar at the top
        }

        sAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        studentRef = db.getReference().child(DatabaseTable.getUsers_table()).child(DatabaseTable.getStudents_table());

        register = (TextView) findViewById(R.id.sRegister);
        btnLogin = (Button) findViewById(R.id.s_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(student.this, StudentMenu.class));
                Login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(student.this, student_register.class);
                startActivity(register);
            }
        });

    }

    private void Login() {
        editEmail = (EditText) findViewById(R.id.s_email);
        editPassword = (EditText) findViewById(R.id.s_password);
        studentLoginLayout = (RelativeLayout) findViewById(R.id.studentLogin);

        //check validation
        if(TextUtils.isEmpty(editEmail.getText().toString()))
        {
            Snackbar.make(studentLoginLayout,"Please Enter Email Address",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText().toString()))
        {
            Snackbar.make(studentLoginLayout,"Please Enter Password",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(editPassword.getText().toString().length() < 6)
        {
            Snackbar.make(studentLoginLayout,"Password is too short",Snackbar.LENGTH_SHORT).show();
            return;
        }

        //Login
        sAuth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //startActivity(new Intent(student.this, StudentMenu.class));
                        //finish();
                        studentRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        //after Assigned value
                                        Common.currentStudent = dataSnapshot.getValue(StudentData.class);
                                        //start new activity
                                        Intent studentMenu = new Intent(student.this, StudentMenu.class);
                                        startActivity(studentMenu);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                    })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar.make(studentLoginLayout,"Login Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
            });


    }

}
