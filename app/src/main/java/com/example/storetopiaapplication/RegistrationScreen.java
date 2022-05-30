package com.example.storetopiaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationScreen extends AppCompatActivity {

    //variables
    EditText regEmail, fullName, regPass,confirmPassword;
    Button register;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        //type casting the variables
        regEmail = findViewById(R.id.edRegEmail);
        fullName = findViewById(R.id.edFullName);
        regPass = findViewById(R.id.edRegPassword);
        confirmPassword = findViewById(R.id.edConfirmPassword);
        register = findViewById(R.id.btnReg);
        mAuth = FirebaseAuth.getInstance();
    }
    public void addItem(View view)
    {
        if ((view.getId() == R.id.btnReg)) {
            String emailAddress = regEmail.getText().toString().trim();
            String pass = regPass.getText().toString().trim();
            String confPass = confirmPassword.getText().toString().trim();


            if (TextUtils.isEmpty(emailAddress)) {
                Toast.makeText(this, "Fill the thing in", Toast.LENGTH_SHORT).show();
                regEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Fill the thing in", Toast.LENGTH_SHORT).show();
                regEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(confPass)) {
                Toast.makeText(this, "Fill the thing in", Toast.LENGTH_SHORT).show();
                regEmail.requestFocus();
                return;
            }

            if (pass.length() < 6 || confirmPassword.length() < 6) {
                Toast.makeText(this, "Password must be 6 characters or more", Toast.LENGTH_SHORT).show();
                regEmail.setText("");
                confirmPassword.setText("");
                return;
            }

            if (confPass.equals(pass)) {
                mAuth.createUserWithEmailAndPassword(emailAddress, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(RegistrationScreen.this, MainActivity.class));
                            Toast.makeText(RegistrationScreen.this, "Great! Your account has been created successfully.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationScreen.this, "Registration has failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}