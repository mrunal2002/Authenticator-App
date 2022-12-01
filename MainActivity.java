package com.example.list_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;


class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText descriptioninput=findViewById(R.id.T2);
        MaterialButton savebtn =findViewById(R.id.T3);

        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description=descriptioninput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                firstnotepage notepage=realm.createObject(firstnotepage.class);
                notepage.setDescription(description);
                notepage.setCreatedtime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_LONG).show();




















            }
        });




    }
}
