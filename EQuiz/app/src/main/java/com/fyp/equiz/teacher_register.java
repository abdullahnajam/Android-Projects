package com.fyp.equiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.Model.StudentData;
import com.fyp.equiz.Model.TeacherData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teacher_register extends AppCompatActivity {
    EditText editEmail, editPassword, editName;
    TextView signIn;
    Button btnRegister;
    RelativeLayout teacherRegisterLayout;

    FirebaseAuth tAuth;
    FirebaseDatabase db;
    DatabaseReference teacherRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titlePurple)); //status bar or the time bar at the top
        }

        tAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        teacherRef = db.getReference().child(DatabaseTable.getUsers_table()).child(DatabaseTable.getTeacher_table());


        btnRegister = (Button) findViewById(R.id.t_btn_register);
        signIn = (TextView) findViewById(R.id.teacher_sign_in);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacher_register.this,teacher.class);
                startActivity(i);
            }
        });

    }

    private void Register() {
        editEmail = (EditText) findViewById(R.id.teacher_email);
        editPassword = (EditText) findViewById(R.id.teacher_password);
        editName = (EditText) findViewById(R.id.teacher_name);
        teacherRegisterLayout = (RelativeLayout) findViewById(R.id.teacherRegister);

        //check validation
        if(TextUtils.isEmpty(editEmail.getText().toString()))
        {
            Snackbar.make(teacherRegisterLayout,"Please Enter Email Address",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText().toString()))
        {
            Snackbar.make(teacherRegisterLayout,"Please Enter Password",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editName.getText().toString()))
        {
            Snackbar.make(teacherRegisterLayout,"Please Enter Name",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(editPassword.getText().toString().length() < 6)
        {
            Snackbar.make(teacherRegisterLayout,"Password is too short",Snackbar.LENGTH_SHORT).show();
            return;
        }


        //register new student
        tAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //save student to db
                        TeacherData teacher = new TeacherData();
                        teacher.setEmail(editEmail.getText().toString());
                        teacher.setPassword(editPassword.getText().toString());
                        teacher.setName(editName.getText().toString());

                        //use email to key
                        teacherRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(teacher)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Snackbar.make(teacherRegisterLayout,"Register Successful",Snackbar.LENGTH_SHORT).show();
                                        Intent teacherMenu = new Intent(teacher_register.this, TeacherMainMenu.class);
                                        startActivity(teacherMenu);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(teacherRegisterLayout,"Register Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(teacherRegisterLayout,"Register Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });

    }
}
