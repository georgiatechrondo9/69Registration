package com.apatel428.a69registration.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apatel428.a69registration.R;
import com.apatel428.a69registration.model.Report;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Matteo on 24/08/2015.
 */
public class RatAdapter extends FirebaseRecyclerAdapter<RatAdapter.ViewHolder, Report> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tBorough;
        TextView tCity;
        TextView tCreatedDate;
        TextView tIncidentAddress;
        TextView tIncidentZip;
        TextView tLatitude;
        TextView tLocationType;
        TextView tLongitude;
        TextView tUniqueKey;

        public ViewHolder(View view) {
            super(view);
            tBorough = (TextView) view.findViewById(R.id.borough);
            tCity = (TextView) view.findViewById(R.id.city);
            tCreatedDate= (TextView) view.findViewById(R.id.created_date);
            tIncidentAddress= (TextView) view.findViewById(R.id.incident_address);
            tIncidentZip= (TextView) view.findViewById(R.id.incident_zip);
            tLatitude= (TextView) view.findViewById(R.id.latitude);
            tLocationType= (TextView) view.findViewById(R.id.location_type);
            tLongitude = (TextView) view.findViewById(R.id.longitude);;
            tUniqueKey = (TextView) view.findViewById(R.id.unique_key);;
        }
    }

    public RatAdapter(Query query, @Nullable ArrayList<Report> items,
                      @Nullable ArrayList<String> keys) {
        super(query, items, keys);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_rat_data, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Report item = getItem(position);
        holder.tUniqueKey.setText(item.getUniqueKey().toString());
        holder.tCreatedDate.setText(String.valueOf(item.getCreatedDate()));
    }

    @Override
    protected void itemAdded(Report item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override
    protected void itemChanged(Report oldItem, Report newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override
    protected void itemRemoved(Report item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override
    protected void itemMoved(Report item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }
}
