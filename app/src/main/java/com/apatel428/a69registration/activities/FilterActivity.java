package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.model.Date;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText startingDate;
    public EditText endingDate;
    public static int[] startDateArray;
    public static int[] endDateArray;
    private Button filterMapButton;
    private Button filterGraphButton;
    public static ArrayList<Date> validDateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initListeners();
    }
    /**
     * This method initializes views
     */
    private void initViews() {
        startingDate = (EditText) findViewById(R.id.startingDate);
        endingDate = (EditText) findViewById(R.id.endingDate);
        filterMapButton = (Button) findViewById(R.id.filterMapButton);
        filterGraphButton = (Button) findViewById(R.id.filterGraphButton);
    }

    /**
     * This method initializes listeners
     */
    private void initListeners() {

        filterMapButton.setOnClickListener(this);
        filterGraphButton.setOnClickListener(this);
    }

    /**
     * The onDataChange method reads from Firebase, and
     * turns the in-range dates into Date objects, then
     * stores them in the validDateArray.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.filterMapButton)) {
            Intent intentFilter = new Intent(getApplicationContext(), MapsActivity.class);
            startFilterDate();
            endFilterDate();
            startActivity(intentFilter);
        } else if (v.getId() == (R.id.filterGraphButton)) {
            Intent intentFilter = new Intent(getApplicationContext(), GraphActivity.class);
            startFilterDate();
            endFilterDate();
            validDateArray = new ArrayList<Date>();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Object createdDate = ds.child("createddate").getValue();
                        int[] dateArray = stringToIntArray(createdDate);
                        if (startDateArray[2] <= dateArray[2] && endDateArray[2] >= dateArray[2]) {
                            if (startDateArray[0] <= dateArray[0] && endDateArray[0] >= dateArray[0]) {
                                Date d = new Date(new int[]{dateArray[0], dateArray[2]});
                                dateCount(d);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            startActivity(intentFilter);
        }
    }

    private int[] stringToIntArray(Object o) {
        String string = o.toString();
        String[] stringArray = string.split("-");
        int[] intArray = new int[stringArray.length];
        for(int i = 0;i < stringArray.length; i++) {
            Integer integer = Integer.parseInt(stringArray[i]);
            intArray[i] = integer.intValue();
        }
        return intArray;
    }

    /**
     * gets the m/d/y of the entered date in int form
     */
    public void startFilterDate() {
        String[] startDateArrayString = startingDate.getText().toString().trim().split("/");
        this.startDateArray = new int[startDateArrayString.length];
        for(int i = 0; i < startDateArrayString.length; i++) {
            Integer startInteger = Integer.parseInt(startDateArrayString[i]);
            this.startDateArray[i] = startInteger.intValue();
        }
    }

    /**
     * gets the m/d/y of the entered date in int form
     */
    public void endFilterDate() {
        String[] endDateArrayString = endingDate.getText().toString().trim().split("/");
        this.endDateArray = new int[endDateArrayString.length];
        for(int i = 0; i < endDateArrayString.length; i++) {
            Integer endInteger = Integer.parseInt(endDateArrayString[i]);
            this.endDateArray[i] = endInteger.intValue();
        }
    }

    /**
     * Adds any new Dates (just month and year) to the ArrayList
     * called validDateArray. If there is already a Date with the
     * same month and year as the one being passed in, then it will
     * simply increment the Date's counter. This is to be used in the
     * graph.
     */
    private void dateCount(Date d) {
        if (validDateArray.contains(d)) {
            validDateArray.get(validDateArray.indexOf(d)).increment();
        } else {
            validDateArray.add(d);
        }
    }
}
