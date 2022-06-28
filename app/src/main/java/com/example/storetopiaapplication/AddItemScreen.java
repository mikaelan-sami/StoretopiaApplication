package com.example.storetopiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddItemScreen extends AppCompatActivity {

    // variables

    EditText team, name, date;
    Button image, item, addItemScreen, btnViewList;
    ImageView viewImage;
    DatabaseReference itemDbRef = FirebaseDatabase.getInstance().getReference("SoccerPlayerInfo");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_screen);

        // typecasting
        team = findViewById(R.id.edTeam);
        name = findViewById(R.id.edPlayerName);
        date = findViewById(R.id.edDate);
        image = findViewById(R.id.btnCamera);
        item = findViewById(R.id.button2);
        viewImage = findViewById(R.id.imageView2);
        addItemScreen = findViewById(R.id.btnAddItemScreen);
        btnViewList = findViewById(R.id.btnView);

        //set on click listener
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewColList();
            }
        });


    }

    private void addItem() {

        CreateCollections cc = new CreateCollections();

        String Name = name.getText().toString();
        String Team = team.getText().toString();
        String Date = date.getText().toString();

        SoccerCollections sc = new SoccerCollections(Name,Date,Team);

        itemDbRef.child(cc.category).child("SoccerPlayers").child(Name).setValue(sc);

        Toast.makeText(AddItemScreen.this, "Card has been successfully added!", Toast.LENGTH_SHORT).show();

    }

    // METHOD TO HANDLE THE BUTTON CLICK EVENT
    public void captureImage(View view)
    {
        Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent3, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);

            // image type
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            viewImage.setImageBitmap(bm);
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Unable to take picture", Toast.LENGTH_SHORT).show();
        }
    }
    public void viewColList(){
        Intent listScreen = new Intent(AddItemScreen.this, displayListView.class);
        startActivity(listScreen);
    }

}

