package com.github.autoservicecourseworkclient.logic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialsResponse {

    private Integer id;
    private String name;
    private double priceCoef;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceCoef() {
        return priceCoef;
    }

    public void setPriceCoef(double priceCoef) {
        this.priceCoef = priceCoef;
    }

    public MaterialsResponse() {
    }

    public MaterialsResponse(Integer id, String name, double priceCoef) {
        this.id = id;
        this.name = name;
        this.priceCoef = priceCoef;
    }

    @Override
    public String toString() {
        return name;
    }
}
