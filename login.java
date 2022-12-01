package com.example.list_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText mEmail, mpassword;
    Button mloginbtn;

    FirebaseAuth fauth;
    Button malreadyac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.personname);
        mpassword = findViewById(R.id.password);
        mloginbtn = findViewById(R.id.create);

        malreadyac = findViewById(R.id.alreadyac);
        fauth= FirebaseAuth.getInstance();
        if(fauth.getCurrentUser()!=null){
            startActivity(new Intent(login.this,main.class));
            finish();

        }





        mloginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mpassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("password is required.");
                    return;

                }
                if (password.length() < 6) {
                    mpassword.setError("Password Must be>= 6 charecters");
                    return;
                }

                fauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            startActivity(new Intent(login.this,main.class));


                        } else {
                            Toast.makeText(com.example.list_application.login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }


        });



        malreadyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.list_application.login.this, main.class));
                Log.d("mrunal","closing app");
            }
        });
    }


}









