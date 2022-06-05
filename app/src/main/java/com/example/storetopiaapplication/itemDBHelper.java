package com.example.storetopiaapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class itemDBHelper {
    private final DatabaseReference dbaseRef;

    public itemDBHelper() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbaseRef = db.getReference("SoccerPlayers");
    }

    public Task<Void> AddPlayerItem(String key, HashMap<String, Object> hashMap) {
        return dbaseRef.child(key).updateChildren(hashMap);
    }

}
