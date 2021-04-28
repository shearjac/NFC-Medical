package com.example.nfcmedical.DBClasses;

public class Medication {

    private int patientId;
    private String name;
    private String dose ="";
    private int frequency = 999;
    private String notes ="";

    public Medication(int patientId, String name, String dose, int frequency, String notes){
        this.patientId = patientId;
        this.name = name;
        this.dose = dose;
        this.frequency = frequency;
        this.notes = notes;
    }

    public Medication(int patientId, String name){
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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return name + " " + dose + " " + frequency + " " + notes;
    }
}
