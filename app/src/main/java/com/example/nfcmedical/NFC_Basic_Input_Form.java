package com.example.nfcmedical;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NFC_Basic_Input_Form#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NFC_Basic_Input_Form extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private char[] multichars;
    private boolean[] conditionsDoDont;
    private String[] customTextInput;
    private boolean filledOut;

    public NFC_Basic_Input_Form() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NFC_Input_Form.
     */
    // TODO: Rename and change types and number of parameters
    public static NFC_Basic_Input_Form newInstance(String param1, String param2) {
        NFC_Basic_Input_Form fragment = new NFC_Basic_Input_Form();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /*View v = getView();

        Button submitButton = (Button) findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compileInformation(v);
            }
        });*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button submitButton = (Button) view.findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compileInformation();
            }
        });
        multichars = new char[3];
        conditionsDoDont = new boolean[38];
        customTextInput = new String[15];
        filledOut = false;
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_n_f_c__basic__input__form, container, false);
    }

    private void compileInformation() {
        final String TAG = "nfchealthinfo";
        View v = getView();
        try {
            ////Blood Type
            RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioBloodType);
            RadioButton choice = (RadioButton)v.findViewById(radioGroup.getCheckedRadioButtonId());
            String bloodType;
            if (choice == null) {
                bloodType = "x";
            } else {
                bloodType = choice.getText().toString();
            }

            char bloodTypeSignature = getBloodTypeSignature(bloodType);

            Log.i(TAG, "Blood type is " + bloodType);
            Log.i(TAG, "Blood type signature is "+Character.toString(bloodTypeSignature));

            multichars[0] = bloodTypeSignature;

            ////Kidney Disease
            CheckBox kidneyDiseaseCheckbox = (CheckBox) v.findViewById(R.id.checkboxChronicKidneyDisorder);
            RadioGroup kidneyStageRadio = (RadioGroup) v.findViewById(R.id.kidneyDiseaseStage);
            RadioButton kidneyStageChoice = (RadioButton)v.findViewById(kidneyStageRadio.getCheckedRadioButtonId());
            String kidneyStage;
            if (kidneyStageChoice == null) {
                kidneyStage = "x";
            } else {
                kidneyStage = kidneyStageChoice.getText().toString();
            }

            char kidneySignature = getKidneyDiseaseSignature(
                    kidneyDiseaseCheckbox.isChecked(),
                    kidneyStage);

            Log.i(TAG, "Kidney disease stage is " + kidneyStage);
            Log.i(TAG, "Kidney disease is checked: " + Boolean.toString(kidneyDiseaseCheckbox.isChecked()));
            Log.i(TAG, "Kidney disease signature is "+Character.toString(kidneySignature));

            multichars[1] = kidneySignature;

            ////Diabetes
            CheckBox diabetesCheck = (CheckBox) v.findViewById(R.id.checkboxDiabetes);
            RadioGroup diabetesType = (RadioGroup) v.findViewById(R.id.diabetesType);
            RadioButton diabetesChoice = (RadioButton) v.findViewById(diabetesType.getCheckedRadioButtonId());
            String diabetesTypeText;

            if (diabetesChoice == null) {
                diabetesTypeText = "x";
            } else {
                diabetesTypeText = diabetesChoice.getText().toString();
            }

            char diabetesSignature = getDiabetesSignature(
                    diabetesCheck.isChecked(),
                    diabetesTypeText);

            Log.i(TAG, "Diabetes type is " + diabetesTypeText);
            Log.i(TAG, "Diabetes is checked: " + Boolean.toString(diabetesCheck.isChecked()));
            Log.i(TAG, "Diabetes signature is "+Character.toString(diabetesSignature));

            multichars[2] = diabetesSignature;

            ////conditionsDoDont
            conditionsDoDont[0] = ((CheckBox)v.findViewById(R.id.checkboxSulfa)).isChecked();
            conditionsDoDont[1] = ((CheckBox)v.findViewById(R.id.checkboxPenicillin)).isChecked();
            conditionsDoDont[2] = ((CheckBox)v.findViewById(R.id.checkboxAmoxicillin)).isChecked();
            conditionsDoDont[3] = ((CheckBox)v.findViewById(R.id.checkboxNSAIDS)).isChecked();
            conditionsDoDont[4] = ((CheckBox)v.findViewById(R.id.checkboxGenAn)).isChecked();
            conditionsDoDont[5] = ((CheckBox)v.findViewById(R.id.checkboxPeanut)).isChecked();
            conditionsDoDont[6] = ((CheckBox)v.findViewById(R.id.checkboxBeeStings)).isChecked();
            conditionsDoDont[7] = ((CheckBox)v.findViewById(R.id.checkboxLatex)).isChecked();
            conditionsDoDont[8] = ((CheckBox)v.findViewById(R.id.checkboxOtherAllergy)).isChecked();

            RadioButton epipenYesNo = (RadioButton)v.findViewById(((RadioGroup)v.findViewById(R.id.radioEpipen)).getCheckedRadioButtonId());
            conditionsDoDont[9] = ( epipenYesNo != null && epipenYesNo.getId() == R.id.radioEpipenYes );

            conditionsDoDont[10] = ((CheckBox)v.findViewById(R.id.checkboxAsthma)).isChecked();
            conditionsDoDont[11] = ((CheckBox)v.findViewById(R.id.checkboxAtrialFibrillation)).isChecked();
            conditionsDoDont[12] = ((CheckBox)v.findViewById(R.id.checkboxCoronaryArteryDisease)).isChecked();
            conditionsDoDont[13] = ((CheckBox)v.findViewById(R.id.checkboxCongestiveHeartFailure)).isChecked();
            conditionsDoDont[14] = ((CheckBox)v.findViewById(R.id.checkboxStroke)).isChecked();
            conditionsDoDont[15] = ((CheckBox)v.findViewById(R.id.checkboxHypertension)).isChecked();
            conditionsDoDont[16] = ((CheckBox)v.findViewById(R.id.checkboxBleedingDisorder)).isChecked();
            conditionsDoDont[17] = ((CheckBox)v.findViewById(R.id.checkboxEpilepsy)).isChecked();
            conditionsDoDont[18] = ((CheckBox)v.findViewById(R.id.checkboxSeizureDisorder)).isChecked();
            conditionsDoDont[19] = ((CheckBox)v.findViewById(R.id.checkboxDementia)).isChecked();
            conditionsDoDont[20] = ((CheckBox)v.findViewById(R.id.checkboxBipolarDisorder)).isChecked();
            conditionsDoDont[21] = ((CheckBox)v.findViewById(R.id.checkboxSchizophrenia)).isChecked();
            conditionsDoDont[22] = ((CheckBox)v.findViewById(R.id.checkboxPTSD)).isChecked();
            conditionsDoDont[23] = ((CheckBox)v.findViewById(R.id.checkboxCancer)).isChecked();
            conditionsDoDont[24] = ((CheckBox)v.findViewById(R.id.checkboxMissingOrgan)).isChecked();
            conditionsDoDont[25] = ((CheckBox)v.findViewById(R.id.checkboxTransplantedOrgan)).isChecked();
            conditionsDoDont[26] = ((CheckBox)v.findViewById(R.id.checkboxOtherLifeThreateningCondition)).isChecked();
            conditionsDoDont[27] = ((CheckBox)v.findViewById(R.id.checkboxOtherCommunicationImpedingCondition)).isChecked();
            conditionsDoDont[28] = ((CheckBox)v.findViewById(R.id.checkboxPacemaker)).isChecked();
            conditionsDoDont[29] = ((CheckBox)v.findViewById(R.id.checkboxDefibrillator)).isChecked();
            conditionsDoDont[30] = ((CheckBox)v.findViewById(R.id.checkboxInsulinPump)).isChecked();
            conditionsDoDont[31] = ((CheckBox)v.findViewById(R.id.checkboxOtherImplantedDevice)).isChecked();
            conditionsDoDont[32] = ((CheckBox)v.findViewById(R.id.checkboxAnticoagulant)).isChecked();
            conditionsDoDont[33] = ((CheckBox)v.findViewById(R.id.checkboxAntiSeizureMedication)).isChecked();
            conditionsDoDont[34] = ((CheckBox)v.findViewById(R.id.checkboxDiabeticMedication)).isChecked();
            conditionsDoDont[35] = ((CheckBox)v.findViewById(R.id.checkboxNarcotic)).isChecked();
            conditionsDoDont[36] = ((CheckBox)v.findViewById(R.id.checkboxNameIncluded)).isChecked();
            conditionsDoDont[37] = ((CheckBox)v.findViewById(R.id.checkboxIncludeIce)).isChecked();

            ////customTextInput
            customTextInput[0] = "DUMMY";
            customTextInput[1] = "DUMMY";
            customTextInput[2] = "DUMMY";
            customTextInput[3] = ((EditText)v.findViewById(R.id.editTextOtherAllergy)).getText().toString();
            customTextInput[4] = ((EditText)v.findViewById(R.id.editTextBleedingDisorder)).getText().toString();
            customTextInput[5] = ((EditText)v.findViewById(R.id.editTextCancerRegion)).getText().toString();
            customTextInput[6] = ((EditText)v.findViewById(R.id.editTextMissingOrgan)).getText().toString();
            customTextInput[7] = ((EditText)v.findViewById(R.id.editTextTransplantedOrgan)).getText().toString();
            customTextInput[8] = ((EditText)v.findViewById(R.id.editTextOtherLifeThreateningCondition)).getText().toString();
            customTextInput[9] = ((EditText)v.findViewById(R.id.editTextOtherCommunicationImpedingConditions)).getText().toString();
            customTextInput[10] = ((EditText)v.findViewById(R.id.editTextOtherImplantedDevice)).getText().toString();
            customTextInput[11] = ((EditText)v.findViewById(R.id.editTextAnticoagulant)).getText().toString();
            customTextInput[12] = ((EditText)v.findViewById(R.id.editTextAntiSeizureMedication)).getText().toString();
            customTextInput[13] = ((EditText)v.findViewById(R.id.editTextDiabeticMedication)).getText().toString();
            customTextInput[14] = ((EditText)v.findViewById(R.id.editTextNarcotic)).getText().toString();

            filledOut = true;

            int i = 0;
            String t = "multichars";
            while (i<multichars.length) {
                Log.v(t,String.format("char at index %d is %c",i,multichars[i]));
                i++;
            }

            i = 0;
            t = "conditionsDoDont";
            while (i<conditionsDoDont.length) {
                Log.v(t,String.format("char at index %d is %b",i,conditionsDoDont[i]));
                i++;
            }

            i = 0;
            t = "customTextInput";
            while (i<customTextInput.length) {
                Log.v(t,String.format("char at index %d is %s",i,customTextInput[i]));
                i++;
            }

        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }

    private char getBloodTypeSignature(String bloodType) {
        if (bloodType.equals("A+")) {return (char)'A';}
        if (bloodType.equals("A-")) {return (char)'a';}
        if (bloodType.equals("B+")) {return (char)'B';}
        if (bloodType.equals("B-")) {return (char)'b';}
        if (bloodType.equals("AB+")) {return (char)'C';}
        if (bloodType.equals("AB-")) {return (char)'c';}
        if (bloodType.equals("O+")) {return (char)'O';}
        if (bloodType.equals("O-")) {return (char)'o';}
        return (char)'x';
    }

    private char getKidneyDiseaseSignature(boolean checked, String kidneyStage) {
        if (checked) {
            if (kidneyStage.equals("I")) {return (char)'1';}
            if (kidneyStage.equals("II")) {return (char)'2';}
            if (kidneyStage.equals("III")) {return (char)'3';}
            if (kidneyStage.equals("IV")) {return (char)'4';}
            return (char)'5';
        }

        return (char)'0';
    }

    private char getDiabetesSignature(boolean checked, String diabetesType) {
        if (checked) {
            if (diabetesType.equals("Type I")) {return (char)'1';}
            if (diabetesType.equals("Type II")) {return (char)'2';}
            return (char)'3';
        }
        return (char)'0';
    }

    public boolean isFilledOut() {
        return filledOut;
    }

    public char[] getMultichars() {
        char[] result = multichars.clone();
        return result;
    }

    public boolean[] getConditionsDoDont() {
        boolean[] result = conditionsDoDont.clone();
        return result;
    }

    public String[] getCustomTextInput() {
        String[] result = customTextInput.clone();
        return result;
    }
}