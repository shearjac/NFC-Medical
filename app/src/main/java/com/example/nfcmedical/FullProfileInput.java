package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FullProfileInput extends AppCompatActivity {
    ///////////////////////////////////////////////////////////////////////////////////////////
    //!!! PATIENT ID MUST BE PASSED INTO THIS CLASS SOMEHOW IN ORDER TO ADD THINGS TO THE DB!!!
    int patientID = 33;

    // new ArrayList to store existing and created text fields
    ArrayList<EditText> allICEContacts = new ArrayList<>();
    ArrayList<EditText> allAllergies = new ArrayList<>();
    ArrayList<EditText> allConditions = new ArrayList<>();
    ArrayList<EditText> allMedications = new ArrayList<>();
    ArrayList<EditText> allImmunizations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_profile_input);


        // add ICE text fields that already exist in layout (order must be preserved)
        EditText originalIceName = (EditText) findViewById(R.id.iCEName);
        EditText originalIceNumber = (EditText) findViewById(R.id.iCENumber);
        allICEContacts.add(originalIceName);
        allICEContacts.add(originalIceNumber);

        // reference button to add another emergency contact
        Button addICE = (Button) findViewById(R.id.addICE);
        addICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addICEContacts(v);
            }
        });



        // add Allergy text fields that already exist in layout (order must be preserved)
        EditText originalAllergen = (EditText) findViewById(R.id.allergen);
        EditText originalSeverity = (EditText) findViewById(R.id.algySeverity);
        allAllergies.add(originalAllergen);
        allAllergies.add(originalSeverity);

        // reference button to add another allergy
        Button addAllergy = (Button) findViewById(R.id.addAllergy);
        addAllergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAllergies(v);
            }
        });



        // add text fields that already exist in layout
        EditText originalCondition = (EditText) findViewById(R.id.condition);
        allConditions.add(originalCondition);

        // reference button to add another allergy
        Button addCondition = (Button) findViewById(R.id.addCondition);
        addCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addConditions(v);
            }
        });



        // add text fields that already exist in layout (order must be preserved)
        EditText originalMedName = (EditText) findViewById(R.id.medicationName);
        EditText originalDose = (EditText) findViewById(R.id.medicationDose);
        EditText originalFrequency = (EditText) findViewById(R.id.medicationFrequency);
        EditText originalMedNotes = (EditText) findViewById(R.id.medicationNotes);
        allMedications.add(originalMedName);
        allMedications.add(originalDose);
        allMedications.add(originalFrequency);
        allMedications.add(originalMedNotes);

        // reference button to add another allergy
        Button addMedication = (Button) findViewById(R.id.addMedication);
        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMedications(v);
            }
        });



        // add text fields that already exist in layout (order must be preserved)
        EditText originalVaccine = (EditText) findViewById(R.id.immunizationName);
        EditText originalVaccineDate = (EditText) findViewById(R.id.immunizationDate);
        allImmunizations.add(originalVaccine);
        allImmunizations.add(originalVaccineDate);

        // reference button to add another allergy
        Button addVaccine = (Button) findViewById(R.id.addVaccine);
        addVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addImmunizations(v);
            }
        });

        // reference submit button to submit all patient input
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputData(patientID, allICEContacts, allAllergies, allConditions, allMedications,
                        allImmunizations);
            }
        }));
    } // end method onCreate


    private void addICEContacts(View v) {

        // reference Linear Layout of the page
        LinearLayout iCELayout = (LinearLayout) findViewById(R.id.iCELayout);

        // create new text fields required to add an emergency contact
        EditText nextICEName = new EditText(FullProfileInput.this);
        EditText nextICENumber = new EditText(FullProfileInput.this);

        // configure layout of new ICE name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        nextICEName.setLayoutParams(params);
        nextICEName.setBackgroundResource(R.drawable.edit_text_border);
        nextICEName.setHint("Name");
        nextICEName.setTextSize(18);

        // configure layout of new ICE number field
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        nextICENumber.setLayoutParams(params2);
        nextICENumber.setBackgroundResource(R.drawable.edit_text_border);
        nextICENumber.setHint("Phone Number");
        nextICENumber.setTextSize(18);

        // add the new text fields to the layout
        iCELayout.addView(nextICEName);
        iCELayout.addView(nextICENumber);

        // add the new text fields to the array list
        allICEContacts.add(nextICEName);
        allICEContacts.add(nextICENumber);
    } // end method addICEContacts


    private void addAllergies(View v) {
        // reference Constraint Layout of the page
        LinearLayout allergyLayout = (LinearLayout) findViewById(R.id.allergyLayout);

        // create new objects required to add an allergy
        EditText nextAllergen = new EditText(FullProfileInput.this);
        EditText nextSeverity = new EditText(FullProfileInput.this);

        // configure layout of new allergen field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        nextAllergen.setLayoutParams(params);
        nextAllergen.setBackgroundResource(R.drawable.edit_text_border);
        nextAllergen.setHint("Allergen");
        nextAllergen.setTextSize(18);

        // configure layout of new severity field

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        nextSeverity.setLayoutParams(params2);
        nextSeverity.setBackgroundResource(R.drawable.edit_text_border);
        nextSeverity.setHint("Enter mild, moderate, or severe");
        nextSeverity.setTextSize(18);

        // add the new text field and dropdown to the layout
        allergyLayout.addView(nextAllergen);
        allergyLayout.addView(nextSeverity);

        // add the new text fields to the ArrayList
        allAllergies.add(nextAllergen);
        allAllergies.add(nextSeverity);
    } // end method addAllergies


    private void addConditions(View v) {
        // reference Linear Layout of the page
        LinearLayout conditionsLayout = (LinearLayout) findViewById(R.id.conditionsLayout);

        // create new objects required to add a new condition
        EditText nextCondition = new EditText(FullProfileInput.this);

        // configure layout of new condition name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        nextCondition.setLayoutParams(params);
        nextCondition.setBackgroundResource(R.drawable.edit_text_border);
        nextCondition.setHint("Name of Condition");
        nextCondition.setTextSize(18);

        // add the new text field to the layout
        conditionsLayout.addView(nextCondition);

        // add the new text field to the ArrayList
        allConditions.add(nextCondition);
    } // end method addConditions


    private void addMedications(View v) {
        // reference Linear Layout of the page
        LinearLayout medicationsLayout = (LinearLayout) findViewById(R.id.medicationsLayout);

        // create new text fields required to add a medication
        EditText nextMedicationName = new EditText(FullProfileInput.this);
        EditText nextMedicationDose = new EditText(FullProfileInput.this);
        EditText nextMedicationFrequency = new EditText(FullProfileInput.this);
        EditText nextMedicationNotes = new EditText(FullProfileInput.this);

        // configure layout of new medication name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        nextMedicationName.setLayoutParams(params);
        nextMedicationName.setBackgroundResource(R.drawable.edit_text_border);
        nextMedicationName.setHint("Name of Medication");
        nextMedicationName.setTextSize(18);

        // add new horizontal LinearLayout for dose & frequency textfields
        LinearLayout doseLayout2 = new LinearLayout(FullProfileInput.this);
        doseLayout2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        doseLayout2.setOrientation(LinearLayout.HORIZONTAL);

        // configure layout of new dosage field
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.weight = 1f;
        params2.setMargins(60, 18, 60, 0);
        nextMedicationDose.setLayoutParams(params2);
        nextMedicationDose.setBackgroundResource(R.drawable.edit_text_border);
        nextMedicationDose.setHint("Dose");
        nextMedicationDose.setTextSize(18);

        // configure layout of new frequency field
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params3.weight = 1f;
        params3.setMargins(0, 18, 60, 0);
        nextMedicationFrequency.setLayoutParams(params3);
        nextMedicationFrequency.setBackgroundResource(R.drawable.edit_text_border);
        nextMedicationFrequency.setHint("Frequency");
        nextMedicationFrequency.setTextSize(18);

        // configure layout of new notes field
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params4.setMargins(60, 18, 60, 0);
        nextMedicationNotes.setLayoutParams(params4);
        nextMedicationNotes.setBackgroundResource(R.drawable.edit_text_border);
        nextMedicationNotes.setHint("Notes");
        nextMedicationNotes.setTextSize(18);

        // add the new text fields to the layout
        medicationsLayout.addView(nextMedicationName);
        medicationsLayout.addView(doseLayout2);
        doseLayout2.addView(nextMedicationDose);
        doseLayout2.addView(nextMedicationFrequency);
        medicationsLayout.addView(nextMedicationNotes);

        // add the new text fields to the ArrayList
        allMedications.add(nextMedicationName);
        allMedications.add(nextMedicationDose);
        allMedications.add(nextMedicationFrequency);
        allMedications.add(nextMedicationNotes);
    }


    private void addImmunizations(View v) {
        // reference Linear Layout of the page
        LinearLayout immunizationsLayout = (LinearLayout) findViewById(R.id.immunizationsLayout);

        // create new text fields required to add an emergency contact
        EditText nextImmunizationName = new EditText(FullProfileInput.this);
        EditText nextImmunizationDate = new EditText(FullProfileInput.this);

        // configure layout of new ICE name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        nextImmunizationName.setLayoutParams(params);
        nextImmunizationName.setBackgroundResource(R.drawable.edit_text_border);
        nextImmunizationName.setHint("Name of Vaccine");
        nextImmunizationName.setTextSize(18);

        // configure layout of new ICE number field
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        nextImmunizationDate.setLayoutParams(params2);
        nextImmunizationDate.setBackgroundResource(R.drawable.edit_text_border);
        nextImmunizationDate.setHint("Date Received (MM/DD/YYYY)");
        nextImmunizationDate.setTextSize(18);
        nextImmunizationDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        // add the new text fields to the layout
        immunizationsLayout.addView(nextImmunizationName);
        immunizationsLayout.addView(nextImmunizationDate);

        // add the new text fields to the ArrayList
        allImmunizations.add(nextImmunizationName);
        allImmunizations.add(nextImmunizationDate);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //!!!WON'T WORK YET BECAUSE I CAN'T ACCESS PATIENT ID OR THE DATABASE!!!
    private void getInputData(int patientID, ArrayList<EditText> allICEContacts,
                              ArrayList<EditText> allAllergies, ArrayList<EditText> allConditions,
                              ArrayList<EditText> allMedications, ArrayList<EditText>allImmunizations){

        final int NUM_OF_ICE_FIELDS = 2;
        final int NUM_OF_ALGY_FIELDS = 2;
        final int NUM_OF_MEDICATION_FIELDS = 4;
        final int NUM_OF_IMMUNIZATION_FIELDS = 2;

        // loop to get all of the ICE Contacts that were input by patient, increment by 2 because
        // there are 2 ICE fields
        for (int i = 0; i < allICEContacts.size(); i += NUM_OF_ICE_FIELDS) {
            EditText nameField = allICEContacts.get(i);
            EditText numberField = allICEContacts.get(i+1);
            String name = nameField.getText().toString();
            String phoneNumber = numberField.getText().toString();
//            // add record to database
//            db.addContact(patientID, name, phoneNumber);
            System.out.println("ICE: " + name +" "+ phoneNumber);
        }

        // loop to get all of the Allergies that were input by patient, increment by 2 because
        // there are 2 allergy fields
        for (int i = 0; i < allAllergies.size(); i += NUM_OF_ALGY_FIELDS) {
            EditText allergenField = allAllergies.get(i);
            EditText severityField = allAllergies.get(i + 1);
            String allergen = allergenField.getText().toString();
            String severity = severityField.getText().toString().toLowerCase();

            // convert severity to integer code
            int severityCode = 0;
            String severityValue = "";
            switch (severity) {
                case "mild":
                    severityValue = "1";
                    break;
                case "moderate":
                    severityValue = "2";
                    break;
                case "severe":
                    severityValue = "3";
                    break;
                default:
                    severityValue = "0";
            }
            if (!severityValue.equals("")){
                severityCode = Integer.valueOf(severityValue);
            }


//            // add record to database (severity is stored as an integer)
//            db.addAllergies(patientID, allergen, severityCode);
            System.out.println("Allergy: " + allergen + " " + severity);
        }

        // loop to get all of the Conditions that were input by patient
        for (int i = 0; i < allConditions.size(); i++) {
            EditText conditionField = allConditions.get(i);
            String condition = conditionField.getText().toString();
//            // add record to database
//            db.addCondition(patientID, condition);
            System.out.println("Condition: " + condition);
        }

        // loop to get all of the Medications that were input by patient, increment by 4 because there
        // are 4 med fields
        for (int i = 0; i < allMedications.size(); i += NUM_OF_MEDICATION_FIELDS) {
            EditText medNameField = allMedications.get(i);
            EditText doseField = allMedications.get(i+1);
            EditText frequencyField = allMedications.get(i+2);
            EditText medNotesField = allMedications.get(i+3);

            String medName = medNameField.getText().toString();
            String dose = doseField.getText().toString();
            String freq = frequencyField.getText().toString();
            if (!freq.equals("")) {
                int frequency = Integer.parseInt(freq);
            }
            String medNotes = medNotesField.getText().toString();

//            // add record to database
//            db.addMedication(patientID, medName, dose, frequency, medNotes);
            System.out.println("Medication: " + medName + " " + dose + " " + freq + " " + medNotes);
        }

        // loop to get all of the Immunizations that were input by patient, increment by 2 because
        // there are 2 immunization fields
        for (int i = 0; i < allImmunizations.size(); i += NUM_OF_IMMUNIZATION_FIELDS) {
            EditText vaccineField = allImmunizations.get(i);
            EditText dateField = allImmunizations.get(i+1);
            String vaccine = vaccineField.getText().toString();
            String date = dateField.getText().toString();

//            // add record to database
//            db.addVaccine(patientID, vaccine, date);
            System.out.println("Vaccine: " + vaccine + " " + date);
        }
    } // end method getInputData

}