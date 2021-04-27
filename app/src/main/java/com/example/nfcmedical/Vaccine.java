package com.example.nfcmedical;

public class Vaccine {

    int patientId;
    String name;
    String date;

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

    public Vaccine(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public Vaccine(int patientId, String name, String date) {
        this.patientId = patientId;
        this.name = name;
        this.date = date;
    }
}
