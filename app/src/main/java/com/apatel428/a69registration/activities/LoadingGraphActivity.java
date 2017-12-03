package com.apatel428.a69registration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.model.IntDate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Map;

import static com.apatel428.a69registration.activities.MapsActivity.stringToIntArray;

public class LoadingGraphActivity extends AppCompatActivity {
    private ArrayList<IntDate> validIntDateArray;
    public static DataPoint[] dpArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        validIntDateArray = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_graph);
        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("data");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("READING");
                System.out.println(dataSnapshot.getChildrenCount());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Object m = ds.getValue();
                    System.out.println(m.getClass().getName());
                    if (m.getClass().getName().equals("java.util.HashMap")) {
                        Map<String, String> map = (Map<String, String>) (m);
                        String createdDate = map.get("createddate");
                        System.out.println(createdDate);
                        if (createdDate != null) {
                            int[] dateArray = stringToIntArray(createdDate);
                            if (dateToInt(FilterActivity.startDateArray) <= dateToInt(dateArray)
                                    && dateToInt(FilterActivity.endDateArray) >= dateToInt(dateArray)) {
                                IntDate d = new IntDate(new int[]{dateArray[0], dateArray[2]});
                                dateCount(d);
                            }
                        }
                    }
                }
                buildData();
                Intent intentFilter = new Intent(getApplicationContext(), GraphActivity.class);
                System.out.println("Throwing to graph");
                startActivity(intentFilter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Read Fail " + databaseError.getMessage());
            }
        });
    }

    /**
     * Adds any new Dates (just month and year) to the ArrayList
     * called validIntDateArray. If there is already a IntDate with the
     * same month and year as the one being passed in, then it will
     * simply increment the IntDate's counter. This is to be used in the
     * graph.
     */
    private void dateCount(IntDate d) {
        if (validIntDateArray.contains(d)) {
            System.out.println("incrementing");
            validIntDateArray.get(validIntDateArray.indexOf(d)).increment();
        } else {
            System.out.println("adding");
            validIntDateArray.add(d);
        }
    }

    public void buildData() {
        dpArray = new DataPoint[validIntDateArray.size()];
        for (int i = 0; i < validIntDateArray.size(); i++) {
            System.out.println("Building dp array");
            double numDate = (double) validIntDateArray.get(i).getDate()[1]
                    + ((double) validIntDateArray.get(i).getDate()[0]/12);
            dpArray[i] = new DataPoint(numDate, validIntDateArray.get(i).getCount());
        }
    }

    public static int dateToInt(int[] i) {
        if (i.length != 3){
            return 0;
        }
        return i[2]*10000 + i[0]*100 + i[1];
    }
}

