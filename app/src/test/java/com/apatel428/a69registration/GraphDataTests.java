package com.apatel428.a69registration;

import com.apatel428.a69registration.activities.FilterActivity;
import com.apatel428.a69registration.model.Date;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created by 14bpo on 11/19/2017.
 */

public class GraphDataTests {
    @Test
    public void arrayListTest() {
        ArrayList<Date> arr = new ArrayList<>();
        Date date1 = new Date(new int[]{10, 17});
        Date date2 = new Date(new int[]{10, 17});
        Date date3 = new Date(new int[]{11, 17});
    }

    @Test
    public void getDataTest() {};
}
