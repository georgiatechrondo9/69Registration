package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.adapters.RatAdapter;
import com.apatel428.a69registration.model.Date;
import com.apatel428.a69registration.model.Report;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.List;

public class RatData extends AppCompatActivity implements View.OnClickListener {
    private Button addReportButton;
    private Button dateFilterButton;
    private RecyclerView ratView;
    private RatAdapter adapter;
    List<MyDataSetGet> listData;
    private FirebaseDatabase FDB;
    private DatabaseReference ref;
    private Button signOutButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data);
        initViews();
        initListeners();
        Query call = FirebaseDatabase.getInstance()
                .getReference()
                .limitToLast(50);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        RatAdapter s = new RatAdapter(call, aKeys, aItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(s);

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
    }

    void getDataFirebase() {

        ref = FDB.getReference();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MyDataSetGet data = new MyDataSetGet();
                data = dataSnapshot.getValue(MyDataSetGet);

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

        List<MyDataSetGet> listArray;

        public void RatAdapter(List<MyDataSetGet> List) {
            this.listArray = List;
        }

        @Override
        public RatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rat_view, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RatAdapter.ViewHolder holder, int position) {
            MyDataSetGet data = listArray.get(position);

            holder.text.setText(data.getHello());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;

            public ViewHolder(View itemView) {
                super(itemView);

                text = (TextView) itemView.findViewById(R.id.rv);
            }
        }

        @Override public int getItemCount() {
            return listArray.size();
        }
    }
}