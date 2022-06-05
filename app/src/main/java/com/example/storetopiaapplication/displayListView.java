package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displayListView extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> categoryList;
    //private Button addCatButton;
    private ListView listViewCat;
    private ArrayAdapter<String> categoryArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listViewCat = findViewById(R.id.soccerPlayerListView);
        categoryList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        myRef = mFirebaseDatabase.getReference("SoccerPlayers");

        categoryArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_text_view, R.id.textView20, categoryList);

        listViewCat.setAdapter(categoryArrayAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String plName = dataSnapshot.child("Player Name").getValue().toString();
                    String plTeam = dataSnapshot.child("Player Team").getValue().toString();
                    String plDate = dataSnapshot.child("Date Aquired").getValue().toString();

                    snapshot.getChildren();
                    categoryList.add("Player Name: " + plName
                            +"\nPlayer Team: " + plTeam
                            +"\nDate Aquired: " + plDate);

                    categoryArrayAdapter.notifyDataSetChanged();


                }
                listViewCat.setAdapter(categoryArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}