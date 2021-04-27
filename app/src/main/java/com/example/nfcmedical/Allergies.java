package com.example.nfcmedical;

public class Allergies {

    int patientId;
    String name;
    int severity = 999;

    public Allergies(int patientId, String name, int severity) {
        this.patientId = patientId;
        this.name = name;
        this.severity = severity;
    }

    public Allergies(int patientId, String name) {
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

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
