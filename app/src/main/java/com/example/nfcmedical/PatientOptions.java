package com.example.nfcmedical;
/*
 * PatientOptions.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class PatientOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_options);


        Button viewBasicButton = (Button) findViewById(R.id.viewBasicButton);
        Button viewFullButton = (Button) findViewById(R.id.viewFullButton);
        Button modifyBasicButton = (Button) findViewById(R.id.modifyBasicButton);
        Button modifyFullButton = (Button) findViewById(R.id.modifyFullButton);

        viewBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanNFC = new Intent(getApplicationContext(), ReadNFC.class);
                startActivity(scanNFC);
            }
        });

        viewFullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayFullProfile = new Intent(getApplicationContext(), FullMedicalProfile.class);
                startActivity(displayFullProfile);
            }
        });

        modifyBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basicInput = new Intent(getApplicationContext(), BasicFormParent.class);
                startActivity(basicInput);
            }
        });

        modifyFullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modifyProfile = new Intent(getApplicationContext(), ModifyFullProfile.class);
                startActivity(modifyProfile);
            }
        });
    }
}