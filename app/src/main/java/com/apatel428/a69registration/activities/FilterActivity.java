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

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText startingDate;
    public EditText endingDate;
    public static int[] startDateArray;
    public static int[] endDateArray;
    private Button filterMapButton;

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
    }

    /**
     * This method initializes listeners
     */
    private void initListeners() {
        filterMapButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.filterMapButton)) {
            Intent intentFilter = new Intent(getApplicationContext(), MapsActivity.class);
            startFilterDate();
            endFilterDate();
            startActivity(intentFilter);
        }
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
}
