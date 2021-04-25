package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        String email = userDetails.get(SessionManager.KEY_EMAIL);

        DB db = new DB();
        int id = db.getId(email);

        Log.d("id", Integer.toString(id));

        run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DB db = new DB(Main.this, progressBar);
                //db.runTest("select * from patient");
                db.login("Lulz3k", "password");
            }
        });
    }

    public void useResult(String myResult){
        Log.d("my final result: ", myResult);
    }

}
