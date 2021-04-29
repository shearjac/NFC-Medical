package com.example.nfcmedical;
/*
 * ModifyFullProfile.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nfcmedical.DBClasses.Allergies;
import com.example.nfcmedical.DBClasses.Condition;
import com.example.nfcmedical.DBClasses.EmergencyContact;
import com.example.nfcmedical.DBClasses.Medication;
import com.example.nfcmedical.DBClasses.Vaccine;

import java.util.HashMap;

public class ModifyFullProfile extends AppCompatActivity {

    DB db = new DB();
    int patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_full_profile);

        patientID = Integer.valueOf(getPatientID());

        EditText modICEName = (EditText) findViewById(R.id.modICEName);
        EditText modICENumber = (EditText) findViewById(R.id.modICENumber);

        EditText modAllergen = (EditText) findViewById(R.id.modAllergen);
        EditText modAlgySeverity = (EditText) findViewById(R.id.modAlgySeverity);

        EditText modCondition = (EditText) findViewById(R.id.modCondition);

        EditText modMedName = (EditText) findViewById(R.id.modMedicationName);
        EditText modMedDose = (EditText) findViewById(R.id.modMedicationDose);
        EditText modMedFreq = (EditText) findViewById(R.id.modMedicationFrequency);
        EditText modMedNotes = (EditText) findViewById(R.id.modMedicationNotes);

        EditText modVaxName = (EditText) findViewById(R.id.modImmunizationName);
        EditText modVaxDate = (EditText) findViewById(R.id.modImmunizationDate);



        // Modify Emergency Contacts
        Button removeICEButton = (Button) findViewById(R.id.removeICEButton);
        removeICEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIceName = modICEName.getText().toString();
                db.removeAllergy(patientID, newIceName);
            }
        });

        Button addICEButton = (Button) findViewById(R.id.addICEButton);
        addICEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIceName = modICEName.getText().toString();
                String newIceNumber = modICEName.getText().toString();
                EmergencyContact newContact = new EmergencyContact(patientID, newIceName, newIceNumber);
                db.addContact(newContact);
            }
        });

        // Modify Allergies
        Button removeAlgyButton = (Button) findViewById(R.id.removeAlgyButton);
        removeAlgyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAllergen = modAllergen.getText().toString();
                db.removeAllergy(patientID, newAllergen);
            }
        });
        Button addAlgyButton = (Button) findViewById(R.id.addAlgyButton);
        addAlgyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAllergen = modAllergen.getText().toString();
                int newAlgySeverity = Integer.valueOf(modAlgySeverity.getText().toString());
                Allergies newAllergy = new Allergies(patientID, newAllergen, newAlgySeverity);
                db.addAllergies(newAllergy);
            }
        });

        // Modify Conditions
        Button removeConditionButton = (Button) findViewById(R.id.removeConditionButton);
        removeConditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCondition = modCondition.getText().toString();
                db.removeCondition(patientID, newCondition);
            }
        });
        Button addConditionButton = (Button) findViewById(R.id.addCondtionButton);
        addConditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newConditionName = modCondition.getText().toString();
                Condition newCondition = new Condition(patientID, newConditionName);
                db.addCondition(newCondition);
            }
        });

        // Modify Medications
        Button removeMedButton = (Button) findViewById(R.id.removeMedButton);
        removeMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMedName = modMedName.getText().toString();
                // no function for removeMedication in db class yet
//                db.removeMedication(patientID, newMedName);
            }
        });
        Button addMedButton = (Button) findViewById(R.id.addMedButton);
        addMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMedName = modMedName.getText().toString();
                String newMedDose = modMedDose.getText().toString();
                int newMedFreq = Integer.valueOf(modMedFreq.getText().toString());
                String newMedNotes = modMedNotes.getText().toString();
                Medication newMedication = new Medication(patientID, newMedName, newMedDose, newMedFreq, newMedNotes);
                db.addMedication(newMedication);
            }
        });

        // Modify Vaccines
        Button removeVaccineButton = (Button) findViewById(R.id.removeVaccineButton);
        removeVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVaxName = modVaxName.getText().toString();
                db.removeCondition(patientID, newVaxName);
            }
        });
        Button addVaccineButton = (Button) findViewById(R.id.addVaccineButton);
        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVaxName = modVaxName.getText().toString();
                String newVaxDate = modVaxDate.getText().toString();
                Vaccine newVaccine = new Vaccine(patientID, newVaxName, newVaxDate);
                db.addVaccine(newVaccine);
            }
        });








        Button modifyDoneButton = (Button) findViewById(R.id.modifyDoneButton);
        modifyDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientOptions.class); //redirect
                startActivity(intent);
            }
        });
    }
    public String getPatientID() {
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();
        String id = userDetails.get(SessionManager.KEY_ID);
        return id;
    }
}