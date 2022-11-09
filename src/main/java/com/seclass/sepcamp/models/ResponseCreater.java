package com.seclass.sepcamp.models;

public class ResponseCreater {
    private  String message;
    private  Boolean success;

    public ResponseCreater() {
    }
    public ResponseCreater(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



}
