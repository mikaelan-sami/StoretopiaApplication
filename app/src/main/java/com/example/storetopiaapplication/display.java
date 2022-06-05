package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

public class display extends AppCompatActivity {

    //private SoccerCollections categories;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> categoryList;
    //private Button addCatButton;
    private ListView listViewCat;
    private ArrayAdapter <String> categoryArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        listViewCat = findViewById(R.id.categoryDisplay);
        categoryList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        myRef = mFirebaseDatabase.getReference("SoccerPlayers");

        categoryArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_tview_list, R.id.listTView, categoryList);

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

        // myRef.addValueEventListener(new ValueEventListener() {
        //  @Override
        //   public void onDataChange(@NonNull DataSnapshot snapshot) {
        //      for (DataSnapshot dataSnapshot : snapshot.getChildren()){
        //          //categories = dataSnapshot.getValue(Categories.class);
        //          String ma = dataSnapshot.child("Player Name").getValue().toString();
        //          String na = dataSnapshot.child("Player Team").getValue().toString();
        //          String mana = dataSnapshot.child("Date Acquired").getValue().toString();
//
        //                  snapshot.getChildren();
        //          categoryList.add("Player Name: " + ma
        //                  +"\nPlayer Team: " +na
        //                  +"\nDate Aquired: " +mana);
//
        //                  categoryArrayAdapter.notifyDataSetChanged();
        //      }
        //      listViewCat.setAdapter(categoryArrayAdapter);
        //  }
//
        //          @Override
        //  public void onCancelled(@NonNull DatabaseError error) {
//
        //          }
        //})//;

    }
}
