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

public class categoryView extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> categoryNameList;
    private ArrayAdapter<String> categoryArrayAdapter;

    //Creating the listview variable
    ListView catListView;

    // Creating data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        catListView = findViewById(R.id.categoryLV);
        categoryNameList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        myRef = mFirebaseDatabase.getReference("SoccerPlayersInfo");
        categoryArrayAdapter = new ArrayAdapter<String>(this, R.layout.categories_layout, R.id.title, categoryNameList);

        catListView.setAdapter(categoryArrayAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String catName = dataSnapshot.child("Category name").getValue().toString();
                    String catGoal = dataSnapshot.child("Goals Set").getValue().toString();

                    snapshot.getChildren();
                    categoryNameList.add("Category name: " + catName
                            +"\nGoals Set: " + catGoal);

                    categoryArrayAdapter.notifyDataSetChanged();


                }
                catListView.setAdapter(categoryArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}