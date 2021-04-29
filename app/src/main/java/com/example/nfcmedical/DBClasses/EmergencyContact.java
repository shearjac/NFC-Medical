package com.example.nfcmedical.DBClasses;

public class EmergencyContact {

    int patientId;
    String name = "";
    String phoneNumber;

    public EmergencyContact(int patientId, String phoneNumber) {
        this.patientId = patientId;
        this.phoneNumber = phoneNumber;
    }

    public EmergencyContact(int patientId, String phoneNumber, String name) {
        this.patientId = patientId;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return name + " " + phoneNumber;
    }
}
