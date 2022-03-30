package com.telemedecineBE.enumeration;

public enum RequestType {
    PRESCRIPTION_REQUEST("PRESCRIPTION_REQUEST"),
    APPOINTMENT_REQUEST("APPOINTMENT_REQUEST");

    private String type;

    RequestType(String type){
        this.type = type;
    }

    public static RequestType findByTypeName(String type){
        switch (type) {
            case "PRESCRIPTION_REQUEST":
            case "PRESCRIPTION":
                return PRESCRIPTION_REQUEST;
            case "APPOINTMENT_REQUEST":
            case "APPOINTMENT":
                return APPOINTMENT_REQUEST;
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
