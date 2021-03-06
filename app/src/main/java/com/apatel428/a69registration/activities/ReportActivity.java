package com.apatel428.a69registration.activities;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.helpers.ActiveUserHolder;
import com.apatel428.a69registration.helpers.InputValidation;
import com.apatel428.a69registration.model.Report;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {
    private EditText address;
    private EditText city;
    private EditText zip;
    private EditText burough;
    private EditText latitude;
    private EditText longitude;
    private Button confirm;
    private Button cancel;
    private DatabaseReference data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        address = (EditText) findViewById(R.id.addressIn);
        city = (EditText) findViewById(R.id.cityIn);
        zip = (EditText) findViewById(R.id.zipIn);
        burough = (EditText) findViewById(R.id.buroughIn);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        confirm = (Button) findViewById(R.id.confirmButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        data = FirebaseDatabase.getInstance().getReference().child("reports");
        System.out.println(data);

        initListeners();
    }

    private void initListeners() {
        confirm.setOnClickListener(click);
        cancel.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirmButton:
                    Report report = new Report();
                    report.setIncidentAddress(address.getText().toString());
                    report.setCity(city.getText().toString());
                    report.setBorough(burough.getText().toString());
                    report.setIncidentZip(zip.getText().toString());
                    report.setLatitude(Long.valueOf(latitude.getText().toString()));
                    report.setLongitude(Long.valueOf(longitude.getText().toString()));
                    report.setUniqueKey(generateKey(report));
                    report.setCreatedDate(generateDate());
                    data.child(generateDataKey()).setValue(report);
                    System.out.println(generateDataKey());
                    Intent intentConfirm = new Intent(getApplicationContext(), RatData.class); //Goes to blank page
                    startActivity(intentConfirm);
                    break;
                case R.id.cancelButton:
                    Intent intentCancel = new Intent(getApplicationContext(), RatData.class); //Goes to blank page
                    startActivity(intentCancel);
                    break;
            }
        }
    };

    private long generateKey(Report report) {
        long key = report.getLatitude() * 23 + report.getLongitude() * 11; //Add lattitude/longitude fields
        return key;
    }

    private String generateDate() {
        String day = String.valueOf(Calendar.getInstance().getTime().getDate());
        String month = String.valueOf(Calendar.getInstance().getTime().getMonth());
        String year = String.valueOf(Calendar.getInstance().getTime().getYear() - 100);

        return month + "-" + day + "-" + year;
    }

    private String generateDataKey() {
        long day = Calendar.getInstance().getTime().getDate();
        long month = Calendar.getInstance().getTime().getMonth();
        long year = Calendar.getInstance().getTime().getYear() - 100;
        long hour = Calendar.getInstance().getTime().getHours();
        long minutes = Calendar.getInstance().getTime().getMinutes();
        long seconds = Calendar.getInstance().getTime().getSeconds();

        return "**999" + String.valueOf(year * 10000000000l +
                month * 100000000l + day * 1000000l + hour * 10000l + minutes * 100 + seconds);
    }
}
