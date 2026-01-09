package com.example.library.exception;

import java.util.Map;

public class ApiErrorResponse {

    private String error;
    private String message;
    private Map<String, String> details;

    public ApiErrorResponse(String error, String message, Map<String, String> details) {
        this.error = error;
        this.message = message;
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
