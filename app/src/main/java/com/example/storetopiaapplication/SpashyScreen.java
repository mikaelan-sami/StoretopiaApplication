package com.example.storetopiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SpashyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashy_screen);

        //Splashy screen --> Set to display for 4s.
        int DISPLAY_LENGTH = 4000;
        new Handler().postDelayed(() -> {
            Intent splashyScreen = new Intent(this, LoginScreen.class);
            startActivity(splashyScreen);
            SpashyScreen.this.finish();
        },DISPLAY_LENGTH);
    }
}