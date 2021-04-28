package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nfcmedical.DBClasses.Medication;

import java.sql.*;
import java.util.HashMap;

public class Main extends AppCompatActivity {

    public Button run;
    public TextView message;
    public ProgressBar progressBar;

    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        run = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();
        String id = userDetails.get(SessionManager.KEY_ID);

        String date = userDetails.get(SessionManager.KEY_DATE);

        String email1 = userDetails.get(SessionManager.KEY_EMAIL);

        run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Medication m = new Medication(1,"name", "dose", 3, "notes");
                String s = m.toString();
                Log.d("log", s);

            }
        });
    }

    public void useResult(String myResult){
        Log.d("my final result: ", myResult);
    }

}
