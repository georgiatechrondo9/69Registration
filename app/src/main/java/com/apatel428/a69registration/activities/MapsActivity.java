package com.apatel428.a69registration.activities;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

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
import com.google.firebase.database.Query;
import com.apatel428.a69registration.R;
import com.google.firebase.database.ValueEventListener;
import com.apatel428.a69registration.model.Report;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Integer> latitudeList;
    private List<Integer> longitudeList;
    private List<String> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        longitudeList = new ArrayList<>();
        latitudeList = new ArrayList<>();
        keyList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("hello");
                System.out.println(dataSnapshot.child("911").child("latitude").getValue().toString());
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Object longitude = ds.child("longitude").getValue();
                    Object latitude = ds.child("latitude").getValue();
                    Object uniquekey = ds.child("uniquekey").getValue();
                    if(longitude != null && latitude != null && uniquekey!=null) {
                        longitudeList.add(Integer.parseInt(longitude.toString()));
                        latitudeList.add(Integer.parseInt(latitude.toString()));
                        keyList.add(uniquekey.toString());
                    }
                }
                LatLng marker = new LatLng(latitudeList.get(0), longitudeList.get(0));
                mMap.addMarker(new MarkerOptions().position(marker).title(keyList.get(0)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
