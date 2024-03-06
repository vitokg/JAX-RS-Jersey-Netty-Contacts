package org.example.app.utils;

public class ResponseMessage {

    private final int status;
    private final boolean success;
    private final String message;

    public ResponseMessage(int status, boolean success, String message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return  "\"response\" : { " +
                "\"status\" : " + status +
                ", \"success\" : " + success +
                ", \"message\" : \"" + message + "\"" +
                "}";
    }
}
