package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {


    TextInputEditText textInputEditTextPassword, textInputEditTextEmail, textInputEditTextFirstName, textInputEditTextLastName;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    String dateSQLCompatible = ""; //this variable will be set after the user changes the date.

    private DatePickerDialog datePickerDialog;
    private MaterialButton dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initDatePicker();
        dateButton = findViewById(R.id.buttonDatePicker);
        dateButton.setText(getTodaysDate());

        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextFirstName = findViewById(R.id.firstName);
        textInputEditTextLastName = findViewById(R.id.lastName);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        progressBar = findViewById(R.id.progress);
        textViewLogin = findViewById(R.id.loginText);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String password, email, firstName, lastName, date;
                password = textInputEditTextPassword.getText().toString();
                email = textInputEditTextEmail.getText().toString();
                firstName = textInputEditTextFirstName.getText().toString();
                lastName = textInputEditTextLastName.getText().toString();

                //dateSQLCompatible = is already set

                if(!email.equals("") && !password.equals("") && !firstName.equals("") && !lastName.equals("") && !dateSQLCompatible.equals("")){
                    progressBar.setVisibility(View.VISIBLE);

                    DB db = new DB(SignUp.this, progressBar);
                    db.signUp(email, password, firstName, lastName, dateSQLCompatible);

                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

                if(month < 10 && day < 10)
                    dateSQLCompatible = year + "-0" + month + "-0" + day;
                else if(month < 10)
                    dateSQLCompatible = year + "-0" + month + "-" + day;
                else if(day < 10)
                    dateSQLCompatible = year + "-" + month + "-0" + day;
                else
                    dateSQLCompatible = year + "-" + month + "-" + day;
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month){
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }



    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}