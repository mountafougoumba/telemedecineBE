package com.telemedecineBE.enumeration;

public enum UserType {
    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    ADMIN("ADMIN");

    private String type;

    UserType(String type){
        this.type=type;
    }

    public static UserType findByName(String name){
        switch (name) {
            case "PATIENT":
                return PATIENT;
            case "DOCTOR":
                return DOCTOR;
            case "ADMIN":
                return ADMIN;
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
