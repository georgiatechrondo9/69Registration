package com.apatel428.a69registration.sql;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 14bpo on 12/4/2017.
 */

public class FirebaseHelper {
    FirebaseDatabase db;
    Query ref;
    boolean check;

    public FirebaseHelper(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference().child("users").orderByChild("email");
    }

    public boolean checkUser(String user, String pass) {
        final String password = pass;
        Query account = ref.equalTo(user);
        account.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object o = dataSnapshot.getValue();
                System.out.println(o);
                if (o != null) {
                    System.out.println(o.getClass().getName());
                    if (o.getClass().getName().equals("java.util.Map")) {
                        Map<String, String> map = (Map<String, String>) o;
                        HashMap<String, String> m = new HashMap<>(map);
                        String pw = m.get("password");
                        check = pw.equals(password);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return check;
    }
}
