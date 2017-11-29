package com.apatel428.a69registration.activities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.model.Date;
import com.apatel428.a69registration.model.Report;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Filter;

public class GraphActivity extends AppCompatActivity {
    public ArrayList<Date> validDateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        validDateArray = new ArrayList<Date>();
        if (FilterActivity.startDateArray != null && FilterActivity.endDateArray
                != null) {
            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    buildData();
                }
            });
            executor.shutdown();
            try {
                executor.awaitTermination(120, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            validDateArray.add(new Date(new int[]{0, 0}));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        ArrayList<Date> datesArrayList = validDateArray;
        DataPoint[] dpArray = new DataPoint[datesArrayList.size()];
        for (int i = 0; i < datesArrayList.size(); i++) {
            int numDate = datesArrayList.get(i).getDate()[1] * 100
                    + datesArrayList.get(i).getDate()[0];
            dpArray[i] = new DataPoint(numDate, datesArrayList.get(i).getCount());
        }
        // Line Graph

        GraphView line_graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> line_series =
                new LineGraphSeries<DataPoint>(dpArray);
        line_graph.addSeries(line_series);

        // set the bound

        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(true);
        line_graph.getViewport().setMinX(0.5);
        line_graph.getViewport().setMaxX(3.5);

        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(true);
        line_graph.getViewport().setMinY(0.5);
        line_graph.getViewport().setMaxY(8);

        line_graph.getViewport().setScrollable(true);
    }

    /**
     * Uses the filter dates to organize valid data
     */
    public void buildData() {
        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("testData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("READING");
                System.out.println(dataSnapshot.getChildrenCount());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Object m = ds.getValue();
                    Map<String,String> map = (Map<String, String>) (m);
                    String createdDate = map.get("createddate");
                    System.out.println(createdDate);
                    if(createdDate != null) {
                        int[] dateArray = stringToIntArray(createdDate);
                        if (FilterActivity.startDateArray[2] <= dateArray[2] && FilterActivity.endDateArray[2] >= dateArray[2]) {
                            if (FilterActivity.startDateArray[0] <= dateArray[0] && FilterActivity.endDateArray[0] >= dateArray[0]) {
                                Date d = new Date(new int[]{dateArray[0], dateArray[2]});
                                dateCount(d);
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Read Fail " + databaseError.getMessage());
            }
        });
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