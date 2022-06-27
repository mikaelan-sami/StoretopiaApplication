package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class categoryView extends AppCompatActivity {
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference myReference;
    private ArrayList<String> categoryNameList;
    private ArrayAdapter<String> categoryArrayAdapter;
    String catID;
    CreateCollections cc = new CreateCollections();

    //Creating the listview variable
    ListView catListView;

    // Creating data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        catListView = findViewById(R.id.categoryLV);
        categoryNameList = new ArrayList<>();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        catID = FirebaseAuth.getInstance().getUid();

        myReference = myFirebaseDatabase.getReference("SoccerPlayerInfo");
        categoryArrayAdapter = new ArrayAdapter<String>(this, R.layout.categories_layout, R.id.title, categoryNameList);

        catListView.setAdapter(categoryArrayAdapter);
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String catName = dataSnapshot.child("catName").getValue().toString();

                    snapshot.getChildren();
                    categoryNameList.add("Category name: " + catName);

                    categoryArrayAdapter.notifyDataSetChanged();


                }
                catListView.setAdapter(categoryArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void itemScreenView(View view){
        Intent itemAdd = new Intent(categoryView.this, AddItemScreen.class);
        startActivity(itemAdd);
    }
}