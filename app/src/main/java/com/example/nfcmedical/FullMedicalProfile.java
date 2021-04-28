package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ExpandableListView;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FullMedicalProfile extends AppCompatActivity {

    ExpandableListView fullMedProfile;
    String[] categories;
    ArrayList<String> profileHeadings = new ArrayList<>();
    HashMap<String, ArrayList<String>> categoryResults = new HashMap<>();
    CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_medical_profile);


        //Variables
        fullMedProfile = findViewById(R.id.fullMedProfile);
        //populate array with names of categories in the full profile
        Resources res = getResources();
        categories = res.getStringArray(R.array.categories);
        //convert to ArrayList
        List<String> headings = Arrays.asList(categories);
        profileHeadings = new ArrayList<>(headings);

        //Variables that will be fetched from database
        ArrayList<Allergies> allergies = new ArrayList<>();
        ArrayList<Condition> condition = new ArrayList<>();
        ArrayList<EmergencyContact> emergencyContact = new ArrayList<>();
        ArrayList<Medication> medication = new ArrayList<>();
        ArrayList<Vaccine> vaccine = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                Connection con = connectionClass(); //Connect to database
                try{
                    String queryAllergies = "SELECT * FROM allergies";
                    String queryCondition = "SELECT * FROM conditions";
                    String queryEmergencyContact = "SELECT * FROM emergency_contact";
                    String queryMedication = "SELECT * FROM medications";
                    String queryVaccine = "SELECT * FROM vaccines";

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(queryAllergies);

                    while(rs.next()){
                        Allergies a = new Allergies(rs.getInt("patientId"), rs.getString("name"), rs.getInt("severity"));
                        allergies.add(a);
                    }

                    rs = stmt.executeQuery(queryCondition);
                    while(rs.next()){
                        Condition c = new Condition(rs.getInt("patientId"), rs.getString("name"));
                        condition.add(c);
                    }

                    rs = stmt.executeQuery(queryEmergencyContact);
                    while(rs.next()){
                        EmergencyContact e = new EmergencyContact(rs.getInt("patientId"), rs.getString("name"), rs.getString("phoneNumber"));
                        emergencyContact.add(e);
                    }

                    rs = stmt.executeQuery(queryMedication);
                    while(rs.next()){
                        Medication m = new Medication(rs.getInt("patientId"), rs.getString("name"), rs.getString("dose"), rs.getInt("frequency"), rs.getString("notes"));
                        medication.add(m);
                    }

                    rs = stmt.executeQuery(queryVaccine);
                    while(rs.next()){
                        Vaccine v = new Vaccine(rs.getInt("patientId"), rs.getString("name"), rs.getString("date"));
                        vaccine.add(v);
                    }

                    stmt.close();
                    con.close();
                } catch (Exception ex){
                    Log.d("sql error", ex.getMessage());
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //____________________________________________________________________________________________________PUT THE CODE HERE

                        //NEED TO MODIFY SO categoryResults IS POPULATED WITH ACTUAL RESULTS FROM QUERYING THE DB
                        //DATA WITH MULTIPLE FIELD NEEDS TO BE CONVERTED TO A STRING THAT WILL DISPLAY ON ONE LINE
                        //FOR EXAMPLE, IN MEDICATIONS: levothyroxine 75 mcg 1x/day

                        //currently using for loop to populate placeholder data items for results in each category
                        for (int i = 0; i < profileHeadings.size(); i++) {
                            //create an arrayList to initially hold child values (database results for this app)
                            ArrayList<String> children = new ArrayList<>();
                            //add 5 placeholder items for each category
                            for (int j = 0; j < 5; j++) {
                                children.add("Item " + j);
                            }
                            //add values to categoryResults Hash Map
                            categoryResults.put(profileHeadings.get(i), children);
                        }

                        adapter = new CategoryAdapter(profileHeadings, categoryResults);
                        fullMedProfile.setAdapter(adapter);


                    }
                });
            }
        });




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