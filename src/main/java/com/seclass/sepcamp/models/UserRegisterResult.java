package com.seclass.sepcamp.models;

public class UserRegisterResult {
    private final String message;
    private final Boolean success;

    public UserRegisterResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public Boolean success() {
        return success;
    }
}
