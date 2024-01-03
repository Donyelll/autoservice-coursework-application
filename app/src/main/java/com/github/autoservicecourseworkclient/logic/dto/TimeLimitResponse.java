package com.github.autoservicecourseworkclient.logic.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeLimitResponse {

    private int id;
    private int duration;
    private double priceCoef;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPriceCoef() {
        return priceCoef;
    }

    public void setPriceCoef(double priceCoef) {
        this.priceCoef = priceCoef;
    }

    public TimeLimitResponse(int id, int duration, double priceCoef) {
        this.id = id;
        this.duration = duration;
        this.priceCoef = priceCoef;
    }

    public TimeLimitResponse() {
    }

    @Override
    public String toString() {
        return duration + " дней";
    }
}
