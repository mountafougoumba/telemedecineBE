package com.telemedecineBE.enumeration;

public enum UserType {
    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    ADMIN("ADMIN");

    private String type;

    UserType(String type){
        this.type=type;
    }

    public UserType findByName(String name){
        switch (name) {
            case "PATIENT":
            case "patient":
                return PATIENT;
            case "DOCTOR":
            case "doctor":
            case "NURSE":
            case "nurse":
                return DOCTOR;
            case "ADMIN":
            case "ADMINISTRATOR":
            case "admin":
            case "administrator":
                return ADMIN;
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
