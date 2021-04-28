package com.example.nfcmedical.DBClasses;

public class Condition {

    int patientId;
    String name;

    public Condition(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
