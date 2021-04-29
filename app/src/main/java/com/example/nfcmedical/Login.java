package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    Button buttonLogin, buttonEmtLogin, buttonScan;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonEmtLogin = findViewById(R.id.buttonEmtLogin);
        buttonScan = findViewById(R.id.buttonScan);
        progressBar = findViewById(R.id.progress);
        textViewSignUp = findViewById(R.id.signUpText);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email, password;
                email = textInputEditTextEmail.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                if(!email.equals("") && !password.equals("")){

                    DB db = new DB(Login.this, progressBar);
                    db.login(email, password);

                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonEmtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(), EmtLogin.class);
                startActivity(intent);
                finish();

            }
        });

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(), ReadNFC.class); //CHANGE THIS TO THE SCAN PAGE
                startActivity(intent);
                finish();

            }
        });

    }
}