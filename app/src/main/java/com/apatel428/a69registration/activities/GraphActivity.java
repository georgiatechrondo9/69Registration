package com.apatel428.a69registration.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apatel428.a69registration.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import static com.apatel428.a69registration.activities.LoadingGraphActivity.dpArray;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Line Graph

        GraphView line_graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> line_series =
                new BarGraphSeries<>(dpArray);
        line_graph.addSeries(line_series);

        // set the bound

        line_series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        line_series.setSpacing(50);
        line_series.setDrawValuesOnTop(true);
        line_series.setValuesOnTopColor(Color.RED);
        // set manual X bounds
        line_graph.getViewport().setXAxisBoundsManual(true);
        line_graph.getViewport().setMinX(15);
        line_graph.getViewport().setMaxX(19);
//
//        // set manual Y bounds
        line_graph.getViewport().setYAxisBoundsManual(true);
        line_graph.getViewport().setMinY(0);
//        line_graph.getViewport().setMaxY(8);

//        line_graph.getViewport().setScrollable(true);
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
}