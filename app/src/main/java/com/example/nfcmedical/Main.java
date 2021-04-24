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

        run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
    }

    public class CheckLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String name1 = "";

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected  void onPostExecute(String r){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Main.this, r, Toast.LENGTH_LONG).show();
            if(isSuccess){
                message = (TextView) findViewById(R.id.textView2);
                message.setText(name1);
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                con = connectionClass(); //Connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                }
                else {
                    //Change below query accordingly
                    String query = "select * from patient";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        name1 = rs.getString("UserName"); //Name in the string label of a column in database
                        z = "query succesfull";
                        isSuccess = true;
                        con.close();
                    } else {
                        z = "Invalid Query";
                        isSuccess = false;
                    }
                }
            }catch (Exception ex){
                isSuccess = false;
                z = ex.getMessage();
                Log.d("sql error", z);
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionClass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://ahcserver.database.windows.net:1433;DatabaseName=ahc;user=ahc@ahcserver;password=SE475project;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch(SQLException se){
            Log.e("error here 1: ", se.getMessage());
        } catch(ClassNotFoundException e){
            Log.e("error here 2: ", e.getMessage());
        } catch(Exception e){
            Log.e("error here 3: ", e.getMessage());
        }
        return connection;
    }

}
