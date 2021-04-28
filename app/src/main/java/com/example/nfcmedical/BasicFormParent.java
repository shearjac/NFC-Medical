package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class BasicFormParent extends AppCompatActivity {
    private NFC_Basic_Input_Form basicForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_form_parent);
        basicForm = (NFC_Basic_Input_Form)getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    public boolean isFilledOut() {
        if (basicForm == null) {return false;}
        return basicForm.isFilledOut();
    }

    public char[] getMultichars() {
        if (basicForm == null) {return new char[3];}
        return basicForm.getMultichars();
    }

    public boolean[] getConditionsDoDont() {
        if (basicForm == null) {return new boolean[38];}
        return basicForm.getConditionsDoDont();
    }

    public String[] getCustomTextInput() {
        if (basicForm == null) {return new String[15];}
        return basicForm.getCustomTextInput();
    }
}