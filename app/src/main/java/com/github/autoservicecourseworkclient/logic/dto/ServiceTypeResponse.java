package com.github.autoservicecourseworkclient.logic.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceTypeResponse {

    private int id;
    private String name;
    private int basePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public ServiceTypeResponse() {
    }

    public ServiceTypeResponse(int id, String name, int basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
