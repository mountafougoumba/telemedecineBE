package com.telemedecineBE.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    ADMIN("ADMIN");

    private String type;

    UserType(String type){
        this.type=type;
    }

    @JsonCreator
    public static UserType getUserType(String value) {

        for (UserType userType : UserType.values()) {

            if (userType.getType().equals(value)) {

                return userType;
            }
        }

        return null;
    }

    public String getType() {
        return type;
    }
}
