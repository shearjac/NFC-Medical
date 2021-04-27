package com.example.nfcmedical;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.HashMap;

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

    private HashMap<String,String> information;

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
            information = new HashMap<>();
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
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_n_f_c__basic__input__form, container, false);
    }

    public void compileInformation() {
        final String TAG = "nfchealthinfo";
        View v = getView();
        try {
            //Blood Type
            RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioBloodType);
            String bloodType = getResources().getResourceEntryName(radioGroup.getCheckedRadioButtonId());
            Log.i(TAG, "Blood type is " + bloodType);
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }
}