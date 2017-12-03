package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.model.Report;
import com.apatel428.a69registration.model.Report;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RatData extends AppCompatActivity implements View.OnClickListener {
    private Button addReportButton;
    private Button dateFilterButton;
    private RecyclerView ratView;
    private RatAdapter adapter;
    List<Report> listData;
    private FirebaseDatabase FDB;
    private Query ref;
    private Button signOutButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data);
        initViews();
        initListeners();

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
//        RatAdapter s = new RatAdapter(call, aKeys, aItems);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(s);

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
        ratView = (RecyclerView) findViewById(R.id.rv);
        ratView.setHasFixedSize(true);
        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
        ratView.setLayoutManager(LM);
        ratView.setItemAnimator(new DefaultItemAnimator());
        ratView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        listData = new ArrayList<>();

        adapter = new RatAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        getDataFirebase();
//        FDB.getReference().setValue(null);
    }

    void getDataFirebase() {
        ref = FDB.getReference().orderByChild("getCreatedDate").limitToFirst(5);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Report data;
                data = dataSnapshot.getValue(Report.class);
                System.out.println(data);

                listData.add(data);
                ratView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addReportButton:
                Intent intentMap = new Intent(getApplicationContext() , ReportActivity.class);
                startActivity(intentMap);
                break;
            case R.id.dateFilterButton:
                Intent intentRegister = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.signOutButton:
                revokeAccess();
                startActivity(new Intent(RatData.this, LoginActivity.class));
        }
    }

    public class RatAdapter extends RecyclerView.Adapter<RatAdapter.ViewHolder>{

        List<Report> listArray;

        public RatAdapter(List<Report> List) {
            this.listArray = List;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rat_view, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RatAdapter.ViewHolder holder, int position) {
            Report data = listArray.get(position);
            holder.created.setText(data.getCreatedDate());
            holder.city.setText(String.valueOf(data.getUniqueKey()));
            holder.burough.setText(data.getBorough());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView created;
            TextView city;
            TextView burough;

            public ViewHolder(View itemView) {
                super(itemView);
                System.out.println("RUNNING");
                created = (TextView) itemView.findViewById(R.id.created_date);
                city = (TextView) itemView.findViewById(R.id.city);
                burough = (TextView) itemView.findViewById(R.id.burough);
            }
        }

        @Override public int getItemCount() {
            return listArray.size();
        }
    }
}