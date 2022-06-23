package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateCollections extends AppCompatActivity {

    EditText categoryName, numGoals;
    Button add, viewCollection;
    DBHelper dbHelper = new DBHelper();
    DatabaseReference rootdatabaseref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        //type casting the variables
        categoryName = findViewById(R.id.edCollectionName);
        numGoals = findViewById(R.id.edGoalsNum);
        add = findViewById(R.id.btnAddCategory);
        viewCollection = findViewById(R.id.categoriesView);

        add.setOnClickListener(View -> {
            HashMap hmap = new HashMap();

            hmap.put("Category name", categoryName.getText().toString());
            hmap.put("Goals Set", numGoals.getText().toString());

            String key = rootdatabaseref.child("SoccerPlayersInfo").push().getKey();
            dbHelper.AddItem(key, hmap).addOnSuccessListener(Success -> {
                Toast.makeText(this, "Your category has been successfully created", Toast.LENGTH_SHORT).show();
                categoryName.setText("");
                numGoals.setText("");
                //Intent items = new Intent(CreateCollections.this, AddItemScreen.class);
                //startActivity(items);
            }).addOnFailureListener(Error -> Toast.makeText(CreateCollections.this, "Failed to add your category", Toast.LENGTH_SHORT).show());
        });

        viewCollection.setOnClickListener(View -> {
            Intent catViewIntent = new Intent(CreateCollections.this,categoryView.class);
            startActivity(catViewIntent);
        });


    }



}