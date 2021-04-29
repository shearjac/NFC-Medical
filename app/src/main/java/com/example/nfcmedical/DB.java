package com.example.nfcmedical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nfcmedical.DBClasses.Allergies;
import com.example.nfcmedical.DBClasses.Condition;
import com.example.nfcmedical.DBClasses.EmergencyContact;
import com.example.nfcmedical.DBClasses.Medication;
import com.example.nfcmedical.DBClasses.Vaccine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DB {

    ProgressBar progressBar = null;
    Connection con = null;
    Context context = null;

    //use this if you need to register, login
    //ProgressBar it is expected a ProgressBar in the Layout. Assign it to a variable in your class (findViewById(R.id.yourBar))
    //Context you can get it with yourClassName.this it is used in this class to handle Toasts
    public DB(Context context, ProgressBar progressBar){
        this.progressBar = progressBar;
        this.context = context;
    }


    public DB(){}

    //_____________________________________________________________________________________________________________FUNCTIONS_START

    public void login(String email, String password){
        Login login = new Login();
        login.execute(email, password);
    }

    public void signUp(String email, String password, String firstName, String lastName, String date){
        Register register = new Register();
        register.execute(email, password, firstName, lastName, date);
    }

    public void emtLogin(String username, String password){
        EmtLogin login = new EmtLogin();
        login.execute(username, password);
    }

    public void addVaccine(Vaccine v){//date format should be YYYY-MM-DD
        String sql = "INSERT INTO vaccines VALUES (" + v.getPatientId() + ", '" + v.getName() + "', '" + v.getDate() + "')";
        updateSQL(sql);
    }

    public void addAllergies(Allergies a){
        String sql = "INSERT INTO allergies VALUES (" + a.getPatientId() + ", '" + a.getName() + "', " + a.getSeverity() + ")";
        updateSQL(sql);
    }

    public void addCondition(Condition c){
        String sql = "INSERT INTO conditions VALUES (" + c.getPatientId() + ", '" + c.getName() + "')";
        updateSQL(sql);
    }

    public void addMedication(Medication m){
        String sql = "INSERT INTO medications VALUES (" + m.getPatientId() + ", '" + m.getName() + "', '" + m.getDose() + "', " + m.getFrequency() + ", '" + m.getNotes() + "')";
        updateSQL(sql);
    }

    public void addContact(EmergencyContact em){
        String sql = "INSERT INTO emergency_contact VALUES (" + em.getPatientId() + ", '" + em.getName() + "', '" + em.getPhoneNumber() + "')";
        updateSQL(sql);
    }

    public void removeVaccine(int patientId, String name){
        String sql = "DELETE FROM vaccines WHERE patient_id=" + patientId + " AND name='" + name + "'";
        updateSQL(sql);
    }

    public void removeAllergy(int patientId, String name){
        String sql = "DELETE FROM allergies WHERE patient_id=" + patientId + " AND name='" + name + "'";
        updateSQL(sql);
    }

    public void removeCondition(int patientId, String name){
        String sql = "DELETE FROM conditions WHERE patient_id=" + patientId + " AND name='" + name + "'";
        updateSQL(sql);
    }

    public void removeContact(int patientId, String name) {
        String sql = "DELETE FROM emergency_contact WHERE patient_id=" + patientId + " AND name='" + name + "'";
        updateSQL(sql);
    }


    public int getId(String email){
        int id = 9999;
        con = connectionClass(); //Connect to database
        String sql = "select id from patient where email= '" + email + "'";

        try{

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id = rs.getInt("id");
                con.close();
            }
        } catch (Exception ex){
            Log.d("sql error", ex.getMessage());
        }

        return id;
    }

    private void updateSQL(String sql){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            con = connectionClass(); //Connect to database
            try{
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                con.close();
            } catch (Exception ex){
                Log.d("sql error", ex.getMessage());
            }
        });
    }


    //_________________________________________________________________________________________________________________CLASSES_START


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
            if(z.equals("Violation of UNIQUE KEY constraint 'UQ__patient__AB6E61642A675C68'. Cannot insert duplicate key in object 'dbo.patient'. The duplicate key value is (newEmail).")){
                Log.d("my error", "is true");
                Toast.makeText(context, "Email already in use", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, r, Toast.LENGTH_LONG).show();
                Log.d("my error", "is false");
            }
            if(isSuccess){
                Log.d("query: ", queryResult);
                Intent intent = new Intent(context, Main.class); //redirect
                context.startActivity(intent);
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
                    stmt.close();

                    String id = Integer.toString(getId(email));

                    //Create session
                    SessionManager sessionManager = new SessionManager(context);
                    sessionManager.createLoginSession(id, email, firstName, lastName, date);

                    isSuccess= true;
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
                Intent intent = new Intent(context, PatientOptions.class); //redirect
                context.startActivity(intent);
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                String email = params[0];
                String password = params[1];
                con = connectionClass(); //Connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                }
                else {
                    //Change below query accordingly
                    String query = "select * from patient where email = '" + email + "' AND password = '" + password + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if(rs.next()){
                        queryResult = rs.getString("email");
                        z = "Login successful";

                        //SESSION VARIABLES
                        String id, firstName, lastName, date;
                        id = Integer.toString(rs.getInt("id"));
                        firstName = rs.getString("firstName");
                        lastName = rs.getString("lastName");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        date = formatter.format(rs.getDate("date"));

                        //Create session
                        SessionManager sessionManager = new SessionManager(context);
                        sessionManager.createLoginSession(id, email, firstName, lastName, date);

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

    //__________________________________________________________________________________EMT_LOGIN
    public class EmtLogin extends AsyncTask<String, String, String>{
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
                Intent intent = new Intent(context, ReadNFC.class); //redirect
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
                    String query = "select * from EMT where username = '" + username + "' AND password = '" + password + "'";
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
