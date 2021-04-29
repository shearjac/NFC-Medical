package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EmtLogin extends AppCompatActivity {

    Button buttonUserLogin, buttonScan, buttonLogin;
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emt_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);

        buttonUserLogin = findViewById(R.id.buttonEMTUserLogin);
        buttonScan = findViewById(R.id.buttonEMTScan);
        buttonLogin = findViewById(R.id.buttonEMTLogin);

        progressBar = findViewById(R.id.progress);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username, password;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                if(!username.equals("") && !password.equals("")){

                    DB db = new DB(EmtLogin.this, progressBar);
                    db.emtLogin(username, password);
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){


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