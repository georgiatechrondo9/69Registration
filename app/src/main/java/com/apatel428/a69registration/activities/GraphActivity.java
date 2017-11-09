package com.apatel428.a69registration.activities;

        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.DashPathEffect;
        import android.graphics.Paint;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.apatel428.a69registration.R;
        import com.apatel428.a69registration.model.Date;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
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

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        ArrayList<Date> datesArrayList = FilterActivity.validDateArray;
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
}