package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.nio.channels.Channel;

public class FullProfileInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_profile_input);

        // reference dropdown for allergy severity and populate it with options
        Spinner algySeverity = findViewById(R.id.algySeverity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.severities,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algySeverity.setAdapter(adapter);
        algySeverity.setOnItemSelectedListener(this);

        // reference button to add another emergency contact
        Button addICE = (Button) findViewById(R.id.addICE);
        addICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addICEContacts(v);
            }
        });

        // reference button to add another allergy
        Button addAllergy = (Button) findViewById(R.id.addAllergy);
        addAllergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAllergies(v);
            }
        });

        // reference button to add another allergy
        Button addCondition = (Button) findViewById(R.id.addCondition);
        addCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConditions(v);
            }
        });

        // reference button to add another allergy
        Button addMedication = (Button) findViewById(R.id.addMedication);
        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMedications(v);
            }
        });

        // reference button to add another allergy
        Button addVaccine = (Button) findViewById(R.id.addVaccine);
        addVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImmunizations(v);
            }
        });
    }

    private void addICEContacts(View v) {
        // reference Constraint Layout of the page
        LinearLayout iCELayout = (LinearLayout) findViewById(R.id.iCELayout);

        // create new text fields required to add an emergency contact
        EditText iCEName2 = new EditText(FullProfileInput.this);
        EditText iCENumber2 = new EditText(FullProfileInput.this);

        // configure layout of new ICE name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        iCEName2.setLayoutParams(params);
        iCEName2.setBackgroundResource(R.drawable.edit_text_border);
        iCEName2.setHint("Name");
        iCEName2.setTextSize(18);

        // configure layout of new ICE number field
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        iCENumber2.setLayoutParams(params2);
        iCENumber2.setBackgroundResource(R.drawable.edit_text_border);
        iCENumber2.setHint("Phone Number");
        iCENumber2.setTextSize(18);

        //add the new text fields to the layout
        iCELayout.addView(iCEName2);
        iCELayout.addView(iCENumber2);
    }

    private void addAllergies(View v) {
        // reference Constraint Layout of the page
        LinearLayout allergyLayout = (LinearLayout) findViewById(R.id.allergyLayout);

        // create new objects required to add an allergy
        EditText allergen2 = new EditText(FullProfileInput.this);
        Spinner algySeverity2 = new Spinner(FullProfileInput.this);

        // configure layout of new allergen field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        allergen2.setLayoutParams(params);
        allergen2.setBackgroundResource(R.drawable.edit_text_border);
        allergen2.setHint("Allergen");
        allergen2.setTextSize(18);

        // configure layout of new severity dropdown
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        algySeverity2.setLayoutParams(params2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.severities,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algySeverity2.setAdapter(adapter);
        algySeverity2.setOnItemSelectedListener(this);

        //add the new text field and dropdown to the layout
        allergyLayout.addView(allergen2);
        allergyLayout.addView(algySeverity2);
    }

    private void addConditions(View v) {
        // reference Constraint Layout of the page
        LinearLayout conditionsLayout = (LinearLayout) findViewById(R.id.conditionsLayout);

        // create new objects required to add a new condition
        EditText condition2 = new EditText(FullProfileInput.this);

        // configure layout of new condition name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        condition2.setLayoutParams(params);
        condition2.setBackgroundResource(R.drawable.edit_text_border);
        condition2.setHint("Name of Condition");
        condition2.setTextSize(18);

        //add the new text field to the layout
        conditionsLayout.addView(condition2);
    }

    private void addMedications(View v) {
        // reference Constraint Layout of the page
        LinearLayout medicationsLayout = (LinearLayout) findViewById(R.id.medicationsLayout);

        // create new text fields required to add a medication
        EditText medicationName2 = new EditText(FullProfileInput.this);
        EditText medicationDose2 = new EditText(FullProfileInput.this);
        EditText medicationFrequency2 = new EditText(FullProfileInput.this);
        EditText medicationNotes2 = new EditText(FullProfileInput.this);

        // configure layout of new medication name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        medicationName2.setLayoutParams(params);
        medicationName2.setBackgroundResource(R.drawable.edit_text_border);
        medicationName2.setHint("Name of Medication");
        medicationName2.setTextSize(18);

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
        medicationDose2.setLayoutParams(params2);
        medicationDose2.setBackgroundResource(R.drawable.edit_text_border);
        medicationDose2.setHint("Dose");
        medicationDose2.setTextSize(18);

        // configure layout of new frequency field
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params3.weight = 1f;
        params3.setMargins(0, 18, 60, 0);
        medicationFrequency2.setLayoutParams(params3);
        medicationFrequency2.setBackgroundResource(R.drawable.edit_text_border);
        medicationFrequency2.setHint("Frequency");
        medicationFrequency2.setTextSize(18);

        // configure layout of new notes field
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params4.setMargins(60, 18, 60, 0);
        medicationNotes2.setLayoutParams(params4);
        medicationNotes2.setBackgroundResource(R.drawable.edit_text_border);
        medicationNotes2.setHint("Notes");
        medicationNotes2.setTextSize(18);

        //add the new text fields to the layout
        medicationsLayout.addView(medicationName2);
        medicationsLayout.addView(doseLayout2);
        doseLayout2.addView(medicationDose2);
        doseLayout2.addView(medicationFrequency2);
        medicationsLayout.addView(medicationNotes2);
    }

    private void addImmunizations(View v) {
        // reference Constraint Layout of the page
        LinearLayout immunizationsLayout = (LinearLayout) findViewById(R.id.immunizationsLayout);

        // create new text fields required to add an emergency contact
        EditText immunizationName2 = new EditText(FullProfileInput.this);
        EditText immunizationDate2 = new EditText(FullProfileInput.this);

        // configure layout of new ICE name field
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(60, 48, 60, 0);
        immunizationName2.setLayoutParams(params);
        immunizationName2.setBackgroundResource(R.drawable.edit_text_border);
        immunizationName2.setHint("Name of Vaccine");
        immunizationName2.setTextSize(18);

        // configure layout of new ICE number field
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(60, 18, 60, 0);
        immunizationDate2.setLayoutParams(params2);
        immunizationDate2.setBackgroundResource(R.drawable.edit_text_border);
        immunizationDate2.setHint("Date Received (MM/DD/YYYY)");
        immunizationDate2.setTextSize(18);
        immunizationDate2.setInputType(InputType.TYPE_CLASS_DATETIME);

        //add the new text fields to the layout
        immunizationsLayout.addView(immunizationName2);
        immunizationsLayout.addView(immunizationDate2);
    }

    //THIS FUNCTION NEEDS TO BE TROUBLE SHOOTED - NOT DONE YET!!!
    private void setEditTextID(String category, int count, EditText textField) {
        String newID = category + String.valueOf(count);
        textField.setId(count);
    }

    //THIS FUNCTION NEEDS TO BE COMPLETED - NOT DONE YET!!!
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //CODE TO HANDLE USER SELECTION FROM ALLERGY SEVERITY
    }

    //THIS FUNCTION NEEDS TO BE COMPLETED - NOT DONE YET!!!
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //CODE TO HANDLE USER SELECTION FROM ALLERGY SEVERITY
    }
}