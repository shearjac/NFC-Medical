package com.example.nfcmedical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Constructor variables info:
//ProgressBar it is expected a ProgressBar in the Layout. Assign it to a variable in your class (findViewById(R.id.yourBar))
//Context you can get it with yourClassName.this it is used in this class to handle Toasts


public class DB {

    ProgressBar progressBar = null;
    Connection con = null;
    Context context = null;

    public DB(Context context, ProgressBar progressBar){
        this.progressBar = progressBar;
        this.context = context;
    }



    public void runTest(String query){
        CheckLogin checkLogin = new CheckLogin();
        checkLogin.execute(query);
    }

    public void login(String username, String password){
        Login login = new Login();
        login.execute(username, password);
    }

    public void signUp(String email, String password, String firstName, String lastName, String date){
        Register register = new Register();
        register.execute(email, password, firstName, lastName, date);
    }


    //__________________________________________________________________________________Register
    public class Register extends AsyncTask<String, String, String>{
        String z = ""; //info on the executed query. Will be shown as a toast.
        Boolean isSuccess = false;
        String queryResult = "";

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected  void onPostExecute(String r){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, r, Toast.LENGTH_LONG).show();
            if(isSuccess){
                Log.d("query: ", queryResult);
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                String email = params[0];
                String password = params[1];
                String firstName = params[2];
                String lastName = params[3];
                String date = params[4];
                con = connectionClass(); //Connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                }
                else {
                    //Change below query accordingly
                    Statement stmt = con.createStatement();
                    String sql = "INSERT INTO patient VALUES('" + email + "','" + password + "','" + firstName + "','" + lastName + "','" + date + "')";
                    stmt.executeUpdate(sql);

                    Intent intent = new Intent(context, Main.class); //redirect
                    context.startActivity(intent);

                }
            }catch (Exception ex){
                isSuccess = false;
                z = ex.getMessage();
                Log.d("sql error", z);
            }
            return z;
        }
    }


    //__________________________________________________________________________________LOGIN
    public class Login extends AsyncTask<String, String, String>{
        String z = ""; //info on the executed query. Will be shown as a toast.
        Boolean isSuccess = false;
        String queryResult = "";

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected  void onPostExecute(String r){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, r, Toast.LENGTH_LONG).show();
            if(isSuccess){
                Log.d("query: ", queryResult);
                Intent intent = new Intent(context, Main.class); //redirect
                context.startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                String username = params[0];
                String password = params[1];
                con = connectionClass(); //Connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                }
                else {
                    //Change below query accordingly
                    String query = "select username from patient where username = '" + username + "' AND password = '" + password + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        queryResult = rs.getString("username");
                        z = "Login successful";
                        isSuccess = true;
                        con.close();
                    } else{
                        z = "Wrong credentials";
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

    //______________________________________________________________________________________Test
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
            Toast.makeText(context, r, Toast.LENGTH_LONG).show();
            if(isSuccess){
                Log.d("query: ", name1);
                //here we should actually return something
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                String query = params[0];
                con = connectionClass(); //Connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                }
                else {
                    //Change below query accordingly
                    //String query = "select * from patient";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        name1 = rs.getString("UserName"); //Name in the string label of a column in database
                        z = "query successful";
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
