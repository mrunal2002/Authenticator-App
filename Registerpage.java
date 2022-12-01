package com.example.list_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

public class Registerpage<FirebaseFirestore> extends AppCompatActivity {

    EditText mfullname,memail,mpassword,mphone;
    Button mRegisterbtn;
    TextView mLoginbtn;
    Button malready;
    FirebaseAuth fauth;
    FirebaseFirestore database;

    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mfullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.Email);
        mpassword = findViewById(R.id.password);
        mphone = findViewById(R.id.phonenumber);
        malready = findViewById(R.id.create);
        mRegisterbtn = findViewById(R.id.registerbtn);
        fauth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        Log.d("userlogin", "===" + fauth.getCurrentUser());
        if (fauth.getCurrentUser() != null) {

            startActivity(new Intent(getApplicationContext(), main.class));
            finish();

        }




        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = memail.getText().toString();

                String password = mpassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email Cannot be Empty");
                    memail.requestFocus();

                }
                else if(TextUtils.isEmpty(password)){
                    mpassword.setError("password Cannot be Empty");
                    mpassword.requestFocus();
                }else{
                    fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                   @Override
                                                                                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                       if (task.isSuccessful()) {
                                                                                                           Toast.makeText(Registerpage.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                                                                           startActivity(new Intent(Registerpage.this, login.class));
                                                                                                       } else {
                                                                                                           Toast.makeText(Registerpage.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                                                                                                       }


                                                                                                       malready.setOnClickListener(new View.OnClickListener() {
                                                                                                           @Override
                                                                                                           public void onClick(View v) {
                                                                                                               startActivity(new Intent(getApplicationContext(), login.class));

                                                                                                           }
                                                                                                       });
                                                                                                   }
                    });
                }
            }
        });
    }
}













