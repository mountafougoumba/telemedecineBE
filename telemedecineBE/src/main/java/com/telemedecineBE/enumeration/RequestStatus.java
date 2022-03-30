package com.telemedecineBE.enumeration;

public enum RequestStatus {
    WAITING("WAITING"),
    CONFIRMED("CONFIRMED"),
    DENIED("DENIED");

    private String status;

    RequestStatus(String status){
        this.status=status;
    }

    public static RequestStatus findByStatusName(String status){
        switch (status) {
            case "WAITING":
                return WAITING;
            case "CONFIRMED":
                return CONFIRMED;
            case "DENIED":
                return DENIED;
        }
        return null;
    }

    public String getStatus() {
        return status;
    }
}
