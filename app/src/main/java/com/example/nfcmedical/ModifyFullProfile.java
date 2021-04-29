package com.example.nfcmedical;
/*
 * ModifyFullProfile.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ModifyFullProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_full_profile);

        // reference dropdown for allergy severity and populate it with options
        Spinner algySeverity = findViewById(R.id.algySeverity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.severities,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algySeverity.setAdapter(adapter);
        algySeverity.setOnItemSelectedListener(this);
    }

    //THIS FUNCTION NEEDS TO BE COMPLETED - NOT DONE YET!!!
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //CODE TO HANDLE USER SELECTION FROM ALLERGY SEVERITY
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}