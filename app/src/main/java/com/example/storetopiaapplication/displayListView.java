package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class displayListView extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> categoryList;
    private ListView listViewItems;
    public static String plName, plTeam, plDate;
    private ArrayAdapter<String> categoryArrayAdapter;
    CreateCollections cc = new CreateCollections();
    categoryView cv = new categoryView();
    PieChart pc;
    Button btnBack, btnitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listViewItems = findViewById(R.id.soccerPlayerListView);
        categoryList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        pc = findViewById(R.id.pieChart);
        btnBack = findViewById(R.id.backBtn);
        btnitems = findViewById(R.id.itemBtn);

        myRef = mFirebaseDatabase.getReference("SoccerPlayerInfo").child(cc.category).child("SoccerPlayers");

        categoryArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_text_view, R.id.textView20, categoryList);

        listViewItems.setAdapter(categoryArrayAdapter);

        //OnClick event for the items within the list(category)
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(displayListView.this, "This item is called" + categoryArrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              int count = (int) snapshot.getChildrenCount();
              displayPieChart(count);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     plDate = dataSnapshot.child("playerTeam").getValue().toString();
                     plName = dataSnapshot.child("playerName").getValue().toString();
                     plTeam = dataSnapshot.child("datePurchased").getValue().toString();

                    snapshot.getChildren();
                    categoryList.add("Player Name: " + plName
                            +"\nPlayer Team: " + plTeam+"\nDate Aquired: " + plDate
                    );

                    categoryArrayAdapter.notifyDataSetChanged();


                }
                listViewItems.setAdapter(categoryArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Back button onclicklistener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        //Add item button onclicklistener
        btnitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemScreen();
            }
        });


    }

    //Takes the user to the add item page if they wish to add more items
    private void itemScreen() {

        Intent ais = new Intent(displayListView.this, AddItemScreen.class);
        startActivity(ais);

    }

    //Method Takes the user back to the previous page
    private void goBack() {
        Intent bck = new Intent(displayListView.this, AddItemScreen.class);
        startActivity(bck);
    }

    //Displays the pie chart - How many items they have & How many items they still need to acquire
    private void displayPieChart(int count) {

        String label = "type";

        int goalAmount = Integer.parseInt(cc.goals);
        int remainingAmount = goalAmount - count;

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        Map<String, Integer> typeAmountMap = new HashMap<>();

        typeAmountMap.put("Cards Collected", count);
        typeAmountMap.put("Cards to Acquire", remainingAmount);

        ArrayList<Integer> colours = new ArrayList<>();
        colours.add(Color.parseColor("#057bf2"));
        colours.add(Color.parseColor("#1d02a6"));

        for (String type: typeAmountMap.keySet())
        {
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        PieDataSet pds = new PieDataSet(pieEntries, label);
        pds.setValueTextSize(10f);
        pds.setColors(colours);
        PieData pd = new PieData(pds);
        pd.setDrawValues(true);

        pc.setData(pd);
        pc.invalidate();

    }
}