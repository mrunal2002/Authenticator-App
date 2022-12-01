package com.example.list_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class finallist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finallist2);


        MaterialButton addbtn=findViewById(R.id.addnewnotebtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(finallist.this,main.class));
            }
        });
        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();


        RealmResults<firstnotepage>notelist = realm.where(firstnotepage.class).findAll();
        RecyclerView recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter ada = new adapter(getApplicationContext(),notelist);
        recyclerView.setAdapter(ada);

        notelist.addChangeListener(new RealmChangeListener<RealmResults<firstnotepage>>() {
            @Override
            public void onChange(RealmResults<firstnotepage> firstnotepages) {
                ada.notifyDataSetChanged();

            }
        });



    }
}