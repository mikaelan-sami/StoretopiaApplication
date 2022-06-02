package com.example.storetopiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateCollections extends AppCompatActivity {

    ListView categories;
    EditText categoryName, numGoals;
    Button addCollection, viewCollection;
    String categoryStore [];
    ArrayList<String> soccerList;
    ArrayAdapter<String> arrayAdapter;
    DBHelper dbHelper = new DBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        //type casting the variables
        categoryName = findViewById(R.id.edCollectionName);
        numGoals = findViewById(R.id.edGoalsNum);
        addCollection = findViewById(R.id.btnAddItem);
        viewCollection = findViewById(R.id.btnViewCollections);

        categories = findViewById(R.id.categoryList);

        DatabaseReference rootdatabaseref = FirebaseDatabase.getInstance().getReference();
        addCollection.setOnClickListener(View -> {
            HashMap hashmap = new HashMap();
            //put method
            hashmap.put("Category Name", categoryName.getText().toString());
            hashmap.put("Goal Set", numGoals.getText().toString());

            String key = rootdatabaseref.child("SoccerPlayers").push().getKey();
            dbHelper.AddItem(key,hashmap).addOnSuccessListener(Success -> {
                Toast.makeText(CreateCollections.this, "Category has been successfully saved", Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, "Category has been successfully saved", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(Error -> Toast.makeText(CreateCollections.this, "Failed to add category", Toast.LENGTH_SHORT).show());

        });
    }
    
}