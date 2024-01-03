package com.github.autoservicecourseworkclient.logic.dto;

public class OrderRequest {

    private Integer customerId;
    private Integer mechanicId;
    private Integer serviceId;
    private Integer timeLimitId;
    private Integer materialsId;

    public OrderRequest setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderRequest setMechanicId(Integer mechanicId) {
        this.mechanicId = mechanicId;
        return this;
    }

    public OrderRequest setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public OrderRequest setTimeLimitId(Integer timeLimitId) {
        this.timeLimitId = timeLimitId;
        return this;
    }

    public OrderRequest setMaterialsId(Integer materialsId) {
        this.materialsId = materialsId;
        return this;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getMechanicId() {
        return mechanicId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Integer getTimeLimitId() {
        return timeLimitId;
    }

    public Integer getMaterialsId() {
        return materialsId;
    }

    public OrderRequest() {
    }

    public OrderRequest(Integer customerId, Integer mechanicId, Integer serviceId, Integer timeLimitId, Integer materialsId) {
        this.customerId = customerId;
        this.mechanicId = mechanicId;
        this.serviceId = serviceId;
        this.timeLimitId = timeLimitId;
        this.materialsId = materialsId;


    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "customerId=" + customerId +
                ", mechanicId=" + mechanicId +
                ", serviceId=" + serviceId +
                ", timeLimitId=" + timeLimitId +
                ", materialsId=" + materialsId +
                '}';
    }
}
