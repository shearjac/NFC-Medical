package com.example.nfcmedical;
/*
 * ReadNFC.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class ReadNFC extends AppCompatActivity {
    /////////////////////////////////////
    boolean isEMT = false; //CHANGE THIS TO GET A FLAG PASSED IN FROM LOG-IN SCREEN

    DataStructuring decoder = new DataStructuring();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_n_f_c);

        // reference submit button to submit all patient input
        Button submitButton = (Button) findViewById(R.id.nfcReadSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //read NFC data String input by user
                EditText nfcReadString = (EditText) findViewById(R.id.nfcReadString);
                String nfcData = nfcReadString.getText().toString();
                //decode the string
                char[] nfcCharData = decoder.decode(nfcData);
                String[] nfcStrings = decoder.expandStrings(nfcCharData);
                HashMap<String, String> nfcBooleanItems = decoder.expandBooleans(nfcCharData);

                ////////////////////////////////////
                //CREATE IF STATEMENT TO FIGURE OUT FROM EMT FLAG WHICH SCREEN TO GO TO NEXT (BASIC OR FULL PROFILE)
                //create an Intent to tell the program which activity (screen) to go to
                Intent displayBasicProfile = new Intent(getApplicationContext(), MedAlertProfile.class);
                //pass data from current activity to new activity in key-value pairs
                Bundle dataBundle = new Bundle();
                dataBundle.putStringArray("stringArray", nfcStrings);
                dataBundle.putSerializable("HashMap", nfcBooleanItems);
                //insert data into intent
                displayBasicProfile.putExtras(dataBundle);
                startActivity(displayBasicProfile);
            }
        });
    } // end method onCreate

} // end class ReadNFC