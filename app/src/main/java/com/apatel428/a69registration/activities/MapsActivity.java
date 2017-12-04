package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apatel428.a69registration.model.Report;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.apatel428.a69registration.R;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import java.util.ArrayList;

import static com.apatel428.a69registration.activities.LoadingGraphActivity.dateToInt;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        reference = FirebaseDatabase.getInstance().getReference();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        getPins(reference);
    }

    public void readData(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure();
            }
        });
    }

    public void getPins(DatabaseReference ref) {
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Log.i("LOCATION", "In snapshot");
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    final int[] startArray = FilterActivity.startDateArray;
                    final int[] endArray = FilterActivity.endDateArray;
                    Object longitude = ds.child("longitude").getValue();
                    Object latitude = ds.child("latitude").getValue();
                    Object uniqueKey = ds.child("uniquekey").getValue();
                    Object createdDate = ds.child("createddate").getValue();
                    if(longitude != null && latitude != null && uniqueKey!=null && createdDate != null) {
                        int[] dateArray = stringToIntArray(createdDate);
                        if (startArray != null && endArray != null) {
                            if (dateToInt(startArray) <= dateToInt(dateArray)
                                    && dateToInt(endArray) >= dateToInt(dateArray)) {
                                LatLng marker = new LatLng((Double) latitude, (Double) longitude);
                                mMap.addMarker(new MarkerOptions().position(marker).title(uniqueKey.toString()));
                                Log.i("ADD","key: " + uniqueKey.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onStart() {
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {
                System.out.println("Failure");
            }
        });
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

    @Override
    public void onStart() {
        MapsActivity.super.onStart();
        Log.d("ONSTART", "Started");
    }
}
