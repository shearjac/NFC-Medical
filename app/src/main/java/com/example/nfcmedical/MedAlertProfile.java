package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MedAlertProfile extends AppCompatActivity {

    private static final String TAG = "MedAlertProfile";

    private ArrayList<String> mLabels = new ArrayList<>();
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<String> mAllergyResults = new ArrayList<>();
    private ArrayList<String> mConditionResults = new ArrayList<>();
    private ArrayList<String> mMedicationResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_alert_profile);
        Log.d(TAG, "onCreate: started");

        //underline headings in GUI
        TextView allergyLabel = (TextView) findViewById(R.id.allergyLabel);
        allergyLabel.setText(Html.fromHtml("<u>Allergies</u>"));

        TextView conditionsLabel = (TextView) findViewById(R.id.medConditionsLabel);
        conditionsLabel.setText(Html.fromHtml("<u>Medical Conditions</u>"));

        TextView medsLabel = (TextView) findViewById(R.id.medicationsLabel);
        medsLabel.setText(Html.fromHtml("<u>High Risk Medications</u>"));

        getPersonalInfo();
        getAllergies();
        getConditions();
        getMedications();
    }

    private void getPersonalInfo() {
        Log.d(TAG, "getPersonalInfo: started");

        //change this to get info by querying database
        //this item will be optional
        mLabels.add("Name:");
        mResults.add("Name Result");

        //this item will be optional
        mLabels.add("ICE Number:");
        mResults.add("ICE Number Result");

        mLabels.add("Blood Type:");
        mResults.add("Blood Type Result");

        loadPersonalInfo();
    }

    private void loadPersonalInfo() {
        Log.d(TAG, "loadPersonalInfo: started");
        RecyclerView personalInfo = findViewById(R.id.personalInfo);
        PersonalInfoAdapter adapter = new PersonalInfoAdapter(this, mLabels, mResults);
        personalInfo.setAdapter(adapter);
        personalInfo.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAllergies() {
        Log.d(TAG, "getAllergies: started");

        //change this to get info by querying database
        mAllergyResults.add("Allergy Result 1");
        mAllergyResults.add("Allergy Result 2");

        loadAllergies();
    }

    private void loadAllergies() {
        Log.d(TAG, "loadAllergies: started");
        RecyclerView allergies = findViewById(R.id.allergies);
        AllergyAdapter adapter = new AllergyAdapter(this, mAllergyResults);
        allergies.setAdapter(adapter);
        allergies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getConditions() {
        Log.d(TAG, "getConditions: started");

        //change this to get info by querying database
        mConditionResults.add("Condition Result 1");
        mConditionResults.add("Condition Result 2");

        loadConditions();
    }

    private void loadConditions() {
        Log.d(TAG, "loadConditions: started");
        RecyclerView medConditions = findViewById(R.id.medConditions);
        AllergyAdapter adapter = new AllergyAdapter(this, mConditionResults);
        medConditions.setAdapter(adapter);
        medConditions.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMedications() {
        Log.d(TAG, "getConditions: started");

        //change this to get info by querying database
        mMedicationResults.add("Medication Result 1");
        mMedicationResults.add("Medication Result 2");

        loadMedications();
    }

    private void loadMedications() {
        Log.d(TAG, "loadMedications: started");
        RecyclerView medications = findViewById(R.id.medications);
        AllergyAdapter adapter = new AllergyAdapter(this, mMedicationResults);
        medications.setAdapter(adapter);
        medications.setLayoutManager(new LinearLayoutManager(this));
    }
}