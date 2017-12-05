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

    public void checkUser(String user, String pass) {
        final String password = pass;
        Query account = ref.equalTo(user);
        account.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot accountSnap = dataSnapshot;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    accountSnap = ds;
                }
                Object o = accountSnap.getValue();
                System.out.println(o);
                if (o != null) {
                    System.out.println(o.getClass().getName());
                    if (o.getClass().getName().equals("java.util.HashMap")) {
                        Map<String, String> map = (Map<String, String>) o;
                        HashMap<String, String> m = new HashMap<>(map);
                        System.out.println(m.keySet());
                        String pw = m.get("password");
                        if (pw == null) {
                            return;
                        }
                        System.out.println("Database Password: " + pw);
                        System.out.println("Checked Password: " + password);
                        check = pw.equals(password);
                        System.out.println(check);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean getCheck() {
        return check;
    }
}
