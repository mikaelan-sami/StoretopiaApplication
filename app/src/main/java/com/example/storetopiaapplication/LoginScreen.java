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
import com.google.firebase.ktx.Firebase;

public class LoginScreen extends AppCompatActivity {

    //variables
    EditText emailAddress, password;
    Button login, register;

    //declaring an instance of firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //type casting the variables
        emailAddress = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegPage);
        mAuth = FirebaseAuth.getInstance();

    }
    public void loginBtn(View view)
    {
        try {
            //Retrieving the email and the password from the edit texts
            String email = emailAddress.getText().toString().trim();
            String pass = password.getText().toString().trim();

            //housekeeping
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter your email to login", Toast.LENGTH_SHORT).show();
                emailAddress.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Enter your password to login", Toast.LENGTH_SHORT).show();
                password.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginScreen.this, "Login Works", Toast.LENGTH_SHORT).show();

                        // Clear the edit text boxes
                        emailAddress.setText("");
                        password.setText("");
                        emailAddress.requestFocus();

                        // take you to the next screen
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(LoginScreen.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        emailAddress.setText("");
                        password.setText("");
                        emailAddress.requestFocus();
                    }
                }
            });

        } catch (Exception e){
            Toast.makeText(this, "Incorrect email or password entered. Please re-enter your details to login. + e", Toast.LENGTH_SHORT).show();
        }
    }
    public void RegScreen(View view)
    {
        try {
            Intent intent = new Intent(this, RegistrationScreen.class);
            startActivity(intent);
            LoginScreen.this.finish();
        } catch (Exception e)
        {
            Toast.makeText(this, "Failed" + e, Toast.LENGTH_SHORT).show();
        }

    }

}