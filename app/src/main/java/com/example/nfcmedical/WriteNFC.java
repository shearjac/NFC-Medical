package com.example.nfcmedical;
/*
 * WriteNFC.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WriteNFC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_n_f_c);

        //get nfc data passed in from previous activity
        Intent intent = getIntent();
        //specify a default value of -1 to indicate if an item is not in the array
        String nfcDataString = intent.getStringExtra("nfcDataString");
        TextView nfcWriteString = (TextView) findViewById(R.id.nfcWriteString);
        nfcWriteString.setText(nfcDataString);

        Button doneButton = (Button) findViewById(R.id.nfcWriteDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientOptions.class); //redirect
                startActivity(intent);
            }
        });
    }
}