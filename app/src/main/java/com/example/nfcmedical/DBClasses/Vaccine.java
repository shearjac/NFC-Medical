package com.example.nfcmedical.DBClasses;

public class Vaccine {

    int patientId;
    String name;
    String date;

    public Vaccine(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public Vaccine(int patientId, String name, String date) {
        this.patientId = patientId;
        this.name = name;
        this.date = date;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return name + " " + getDateFormat(date);
    }

    //GETS YYYY-MM-DD
    //RETURNS Day Month Year
    private String getDateFormat(String date){
        String newDate;
        String day;
        String month;
        String year;
        year = String.valueOf(date.charAt(0)) + String.valueOf(date.charAt(1)) + String.valueOf(date.charAt(2)) + String.valueOf(date.charAt(1));
        day = String.valueOf(date.charAt(8)) + String.valueOf(date.charAt(9));
        int monthInt = Integer.parseInt(String.valueOf(date.charAt(5))+ String.valueOf(date.charAt(6)));
        month = getMonthFormat(monthInt);
        return day + " " + month + " " + year;
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

}
