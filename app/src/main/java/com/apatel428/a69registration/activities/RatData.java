package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.adapters.RatAdapter;
import com.apatel428.a69registration.model.Report;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class RatData extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference data;
    private ArrayList<Report> aKeys;
    private ArrayList<String> aItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data);
        Query call = FirebaseDatabase.getInstance()
                .getReference()
                .child("cs2340-f8f6d")
                .limitToLast(50);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        RatAdapter s = new RatAdapter(call, aKeys, aItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMap:
                Intent intentMap = new Intent(getApplicationContext() , MapsActivity.class);
                startActivity(intentMap);
                break;
            case R.id.dateFilter:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
