package com.github.autoservicecourseworkclient.logic.dto;

public class InfoResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public InfoResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public InfoResponse() {
    }

    public InfoResponse(String message) {
        this.message = message;
    }
}
