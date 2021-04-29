package com.example.nfcmedical;
/*
 * MedAlertProfile.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MedAlertProfile extends AppCompatActivity {
    private static final String TAG = "MedAlertProfile";

//    /////////////////////////      REMOVE THIS AFTER TESTING //////////////////////////////////////////
//    private String[] testStrings = new String[]{"John Smith", "Jane Smith", "3940983627", "A+", "Stage III",
//            null, "bad group members", null, "liver cancer", null, null, "primary myelofibrosis", "deaf",
//            null, "coumadin", null, "metformin", null};
//    private HashMap<String, String> testHashMap = new HashMap<>();



    private String[] nfcStrings = new String[19];
    private HashMap<String, String> nfcBooleanItems = new HashMap<>();

    private ArrayList<String> mLabels = new ArrayList<>();
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<String> mAllergyResults = new ArrayList<>();
    private ArrayList<String> mConditionResults = new ArrayList<>();
    private ArrayList<String> mMedicationResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_alert_profile);

        // get data passed from previous activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nfcStrings = bundle.getStringArray("stringArray");
            nfcBooleanItems = (HashMap<String, String>) bundle.getSerializable("HashMap");
        }

/////////////////////////////////    REMOVE AFTER TESTING ////////////////////////////////////////////
//        testHashMap.put("NSAIDs allergy", "true");
//        testHashMap.put("Atrial Fibrillation", "true");
//        testHashMap.put("Stroke", "true");
//        testHashMap.put("Pacemaker", "true");
//        testHashMap.put("General Anesthesia allergy", "true");
//        testHashMap.put("Wrong input on purpose", "true");
//        testHashMap.put("Peanut allergy", "false");
//        testHashMap.put("Coronary Artery Disease", "false");
//        testHashMap.put("Hypertension", "false");
//        testHashMap.put("Insulin Pump", "false");
//        testHashMap.put("PTSD", "false");
//        testHashMap.put("Wrong input on purpose", "false");

        //underline headings in GUI
        TextView allergyLabel = (TextView) findViewById(R.id.allergyLabel);
        allergyLabel.setText(Html.fromHtml("<u>Allergies</u>"));

        TextView conditionsLabel = (TextView) findViewById(R.id.medConditionsLabel);
        conditionsLabel.setText(Html.fromHtml("<u>Medical Conditions</u>"));

        TextView medsLabel = (TextView) findViewById(R.id.medicationsLabel);
        medsLabel.setText(Html.fromHtml("<u>High Risk Medications</u>"));

        processBooleanData(nfcBooleanItems);
        getPersonalInfo();
        getAllergies();
        getConditions();
        getMedications();
    }

    private void getPersonalInfo() {
        Log.d(TAG, "getPersonalInfo: started");

/////////////////////////////////     Currently Shows Heading but not result     ///////////////////////////
//        //this item will be optional
//        String showName = nfcBooleanItems.get("Show Name");
//        if (showName.equals("true")) {
//            String nameResult = nfcStrings[0];
//            mLabels.add("Name:");
//            mResults.add(nameResult);
//        }
//        //this item will be optional
//            String showICE = nfcBooleanItems.get("Show ICE Number");
//        if (showICE.equals("true")) {
//            String iceNumberResult = nfcStrings[2];
//            mLabels.add("ICE Number:");
//            mResults.add(iceNumberResult);
//        }

        mLabels.add("Blood Type:");
        // get blood type from designated spot in array
        mResults.add(nfcStrings[3]);

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

        //get custom entered allergies from string data on nfc
        if (nfcStrings[6]!= null){
            mAllergyResults.add(nfcStrings[6]);
        }

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

        if (nfcStrings[4] != null){
            mConditionResults.add("CKD - " + nfcStrings[4]);
        }

        if (nfcStrings[5] != null){
            mConditionResults.add("Diabetes - " + nfcStrings[5]);
        }

        final int FIRST_CONDITION_ENTRY = 7;
        final int LAST_CONDITION_ENTRY = 13;
        //get custom entered conditions from string data on nfc
        for (int i = FIRST_CONDITION_ENTRY; i <= LAST_CONDITION_ENTRY; i++)
        if (nfcStrings[i] != null){
            mConditionResults.add(nfcStrings[i]);
        }

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

        //get custom entered medications from string data on nfc
        final int FIRST_MEDICATION_ENTRY = 14;
        final int LAST_MEDICATION_ENTRY = 17;
        //get custom entered conditions from string data on nfc
        for (int i = FIRST_MEDICATION_ENTRY; i <= LAST_MEDICATION_ENTRY; i++)
            if (nfcStrings[i] != null){
                mMedicationResults.add(nfcStrings[i]);
            }

        loadMedications();
    }

    private void loadMedications() {
        Log.d(TAG, "loadMedications: started");
        RecyclerView medications = findViewById(R.id.medications);
        AllergyAdapter adapter = new AllergyAdapter(this, mMedicationResults);
        medications.setAdapter(adapter);
        medications.setLayoutManager(new LinearLayoutManager(this));
    }

    private void processBooleanData(HashMap<String, String> nfcBooleanItems) {
        Log.d(TAG, "processBooleanData: started");
      // get all values of nfcBooleanItems
      // iterate through all the values that == "true" and add the key to the appropriate result list
        Iterator iterator = nfcBooleanItems.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry nextElement = (Map.Entry) iterator.next();
            String value = nextElement.getValue().toString();

            if (value.equals("true")) {
                String key = nextElement.getKey().toString().toLowerCase();

                switch(key){
                    // Allergies
                    case "sulfa drugs allergy": mAllergyResults.add("sulfa drugs");
                        break;
                    case "penicillin allergy": mAllergyResults.add("Penicillin");
                        break;
                    case "amoxicillin allergy": mAllergyResults.add("Amoxicillin");
                        break;
                    case "nsaids allergy": mAllergyResults.add("NSAIDs");
                        break;
                    case "general anesthetics allergy": mAllergyResults.add("general anesthetics");
                        break;
                    case "peanut allergy": mAllergyResults.add("peanuts");
                        break;
                    case "bee sting allergy": mAllergyResults.add("bee stings");
                        break;
                    case "latex allergy": mAllergyResults.add("latex");
                        break;
                    case "carries epi-pen?": mAllergyResults.add("Carries Epi-pen");
                        break;
                    // Medical Conditions
                    case "asthma": mConditionResults.add(key);
                        break;
                    case "atrial fibrillation": mConditionResults.add(key);
                        break;
                    case "coronary artery disease": mConditionResults.add(key);
                        break;
                    case "stroke": mConditionResults.add(key);
                        break;
                    case "hypertension": mConditionResults.add(key);
                        break;
                    case "epilepsy": mConditionResults.add(key);
                        break;
                    case "seizure disorder": mConditionResults.add(key);
                        break;
                    case "dementia": mConditionResults.add(key);
                        break;
                    case "bipolar disorder": mConditionResults.add(key);
                        break;
                    case "schizophrenia": mConditionResults.add(key);
                        break;
                    case "ptsd": mConditionResults.add("PTSD");
                        break;
                    case "pacemaker": mConditionResults.add("implanted pacemaker");
                        break;
                    case "defibrillator": mConditionResults.add("implanted defibrillator");
                        break;
                    case "insulin pump": mConditionResults.add(key);
                        break;
                    default:
                }
            }
        }
    }

}
