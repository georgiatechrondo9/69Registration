package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.apatel428.a69registration.R;
import com.apatel428.a69registration.adapters.RatAdapter;
import com.apatel428.a69registration.model.Report;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.apatel428.a69registration.activities.LoadingGraphActivity.dateToInt;

public class RatData extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference data;
    private ArrayList<Report> aKeys;
    private ArrayList<String> aItems;
    private Button addReportButton;
    private Button dateFilterButton;
    private Button signOutButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    static final ArrayList<Report> reportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data);
        initViews();
        initListeners();
        Query call = FirebaseDatabase.getInstance()
                .getReference()
                .child("cs2340-f8f6d")
                .limitToLast(50);
        /*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        RatAdapter s = new RatAdapter(call, aKeys, aItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(s);
         */

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        mAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        addReportButton = (Button) findViewById(R.id.addReportButton);
        dateFilterButton = (Button) findViewById(R.id.dateFilterButton);
        signOutButton = (Button) findViewById(R.id.signOutButton);
    }

    private void initListeners() {
        addReportButton.setOnClickListener(this);
        dateFilterButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess();

        LoginManager.getInstance().logOut();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addReportButton:
                Intent intentMap = new Intent(getApplicationContext() , ReportActivity.class);
                startActivity(intentMap);
                break;
            case R.id.dateFilterButton:
                // Navigate to FilterActivity
                Intent intentRegister = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.signOutButton:
                revokeAccess();
                startActivity(new Intent(RatData.this, LoginActivity.class));
        }
    }
    /**
     *
     * @param o object with date
     * @return int array [m,d,y]
     */
    public static int[] stringToIntArray(Object o) {
        String string = o.toString();
        String[] stringArray = string.split("-");
        int[] intArray = new int[stringArray.length];
        for(int i = 0;i < stringArray.length; i++) {
            Integer integer = Integer.parseInt(stringArray[i]);
            intArray[i] = integer.intValue();
        }
        return intArray;
    }
}
