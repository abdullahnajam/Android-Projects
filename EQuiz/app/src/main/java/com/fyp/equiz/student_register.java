package com.fyp.equiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.equiz.Model.DatabaseTable;
import com.fyp.equiz.Model.StudentData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_register extends AppCompatActivity {

    EditText editEmail, editPassword, editName, editEnrollment;
    TextView signIn;
    Button btnRegister;
    RelativeLayout studentRegisterLayout;

    FirebaseAuth sAuth;
    FirebaseDatabase db;
    DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        if (Build.VERSION.SDK_INT >= 21) { // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.titleGreen)); //status bar or the time bar at the top
        }

        sAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        studentRef = db.getReference().child(DatabaseTable.getUsers_table()).child(DatabaseTable.getStudents_table());


        btnRegister = (Button) findViewById(R.id.s_btn_register);
        signIn = (TextView) findViewById(R.id.student_sign_in);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(student_register.this,student.class);
                startActivity(i);
            }
        });

    }

    private void Register() {
        editEmail = (EditText) findViewById(R.id.student_email);
        editPassword = (EditText) findViewById(R.id.student_password);
        editName = (EditText) findViewById(R.id.student_name);
        editEnrollment = (EditText) findViewById(R.id.student_enrollment);
        studentRegisterLayout = (RelativeLayout) findViewById(R.id.studentRegister);

        //check validation
        if(TextUtils.isEmpty(editEmail.getText().toString()))
        {
            Snackbar.make(studentRegisterLayout,"Please Enter Email Address",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText().toString()))
        {
            Snackbar.make(studentRegisterLayout,"Please Enter Password",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editName.getText().toString()))
        {
            Snackbar.make(studentRegisterLayout,"Please Enter Name",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(editEnrollment.getText().toString()))
        {
            Snackbar.make(studentRegisterLayout,"Please Enter Enrollment",Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(editPassword.getText().toString().length() < 6)
        {
            Snackbar.make(studentRegisterLayout,"Password is too short",Snackbar.LENGTH_SHORT).show();
            return;
        }


        //register new student
        sAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //save student to db
                        StudentData student = new StudentData();
                        student.setEmail(editEmail.getText().toString());
                        student.setPassword(editPassword.getText().toString());
                        student.setName(editName.getText().toString());
                        student.setEnrollment(editEnrollment.getText().toString());

                        //use email to key
                        studentRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(student)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(studentRegisterLayout,"Register Successful",Snackbar.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(studentRegisterLayout,"Register Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(studentRegisterLayout,"Register Failed! \n"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
