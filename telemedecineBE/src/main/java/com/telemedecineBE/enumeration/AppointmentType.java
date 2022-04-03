package com.telemedecineBE.enumeration;

public enum AppointmentType {
    IN_PERSON ("IN_PERSON"),
    ONLINE ("ONLINE");

    private String type;

    AppointmentType(String type){
        this.type = type;
    }

    public static AppointmentType findByTypeName(String type){
        switch (type) {
            case "IN_PERSON":
                return IN_PERSON;
            case "ONLINE":
                return ONLINE;
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
