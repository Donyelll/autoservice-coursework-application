package com.github.autoservicecourseworkclient.logic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

    private int id;
    private CustomerDto customer;
    private MechanicDto mechanic;
    private ServiceTypeResponse service;
    private TimeLimitResponse timeLimit;
    private MaterialsResponse materials;
    private int totalPrice;

    @Override
    public String toString() {
        return
                "Номер заказа: " + id +
                ", \nКлиент: " + customer.getSurname() +
                ", \nМеханик: " + mechanic.getSurname() +
                ", \nУслуга: " + service.getName() +
                ", \nВремя выполнения: " + timeLimit.getDuration() + " дней" +
                ", \nМатериалы: " + materials.getName() +
                ", \nОбщая цена: " + totalPrice + " рублей";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public MechanicDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicDto mechanic) {
        this.mechanic = mechanic;
    }

    public ServiceTypeResponse getService() {
        return service;
    }

    public void setService(ServiceTypeResponse service) {
        this.service = service;
    }

    public TimeLimitResponse getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(TimeLimitResponse timeLimit) {
        this.timeLimit = timeLimit;
    }

    public MaterialsResponse getMaterials() {
        return materials;
    }

    public void setMaterials(MaterialsResponse materials) {
        this.materials = materials;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderResponse() {
    }

    public OrderResponse(int id, CustomerDto customer, MechanicDto mechanic, ServiceTypeResponse service, TimeLimitResponse timeLimit, MaterialsResponse materials, int totalPrice) {
        this.id = id;
        this.customer = customer;
        this.mechanic = mechanic;
        this.service = service;
        this.timeLimit = timeLimit;
        this.materials = materials;
        this.totalPrice = totalPrice;
    }
}
