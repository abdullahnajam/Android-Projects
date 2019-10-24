package com.fyp.equiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyp.equiz.Model.Common;
import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.Model.TeacherData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class teacher extends AppCompatActivity {

    Typeface fonts1;
    CheckBox remember;

    EditText editEmail, editPassword;
    String tEmail;
    TextView register;
    Button btnLogin;
    RelativeLayout teacherLoginLayout;

    FirebaseAuth tAuth;
    FirebaseDatabase db;
    DatabaseReference teacherRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);

        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }

        tAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        teacherRef = db.getReference().child(DatabaseTable.getUsers_table()).child(DatabaseTable.getTeacher_table());

        register = (TextView) findViewById(R.id.tRegister);
        btnLogin = (Button) findViewById(R.id.t_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(teacher.this, TeacherMainMenu.class));
                Login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(teacher.this, teacher_register.class);
                startActivity(register);
            }
        });

    }

    private void Login() {
        editEmail = (EditText) findViewById(R.id.t_email);
        editPassword = (EditText) findViewById(R.id.t_password);
        tEmail = editEmail.getText().toString();
        teacherLoginLayout = (RelativeLayout) findViewById(R.id.teacherLogin);

        //check validation
        if(TextUtils.isEmpty(editEmail.getText().toString()))
        {
            Snackbar.make(teacherLoginLayout,"Please Enter Email Address",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText().toString()))
        {
            Snackbar.make(teacherLoginLayout,"Please Enter Password",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(editPassword.getText().toString().length() < 6)
        {
            Snackbar.make(teacherLoginLayout,"Password is too short",Snackbar.LENGTH_SHORT).show();
            return;
        }

        //Login
        tAuth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        /*Intent teacherMenu = new Intent(teacher.this, TeacherMainMenu.class);
                        //teacherMenu.putExtra("email",tEmail.toString());
                        startActivity(teacherMenu);
                        finish();*/
                        teacherRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        //after Assigned value
                                        Common.currentTeacher = dataSnapshot.getValue(TeacherData.class);
                                        //start new activity
                                        Intent teacherMenu = new Intent(teacher.this, TeacherMainMenu.class);
                                        startActivity(teacherMenu);
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
                Snackbar.make(teacherLoginLayout,"Login Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });


    }

}

