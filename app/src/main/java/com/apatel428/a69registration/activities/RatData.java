package com.apatel428.a69registration.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apatel428.a69registration.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RatData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data);
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("cs2340-f8f6d")
                .limitToLast(50);
    }
}
