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
    public static String category, goals;
    DatabaseReference catDbRef = FirebaseDatabase.getInstance().getReference("SoccerPlayerInfo");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        //type casting the variables
        categoryName = findViewById(R.id.edCollectionName);
        numGoals = findViewById(R.id.edGoalsNum);
        add = findViewById(R.id.btnAddCategory);
        viewCollection = findViewById(R.id.categoriesView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCatData();
            }
        });

        viewCollection.setOnClickListener(View -> {
            Intent catViewIntent = new Intent(CreateCollections.this,categoryView.class);
            startActivity(catViewIntent);
        });


    }

    private void insertCatData() {
        category = categoryName.getText().toString();
        goals = numGoals.getText().toString();

        categoryClass ct = new categoryClass(category, goals);

        catDbRef.child(category).setValue(ct);

        Toast.makeText(this, "Category has been succesfully added!", Toast.LENGTH_SHORT).show();

    }


}