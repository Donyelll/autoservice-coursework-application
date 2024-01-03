package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.MaterialsResponse;
import com.github.autoservicecourseworkclient.logic.dto.MechanicDto;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;
import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MechanicController {


    @GET("mechanics/")
    Call<List<UserCredentialsResponse>> getCredentials();

    @GET("mechanics/{id}/services")
    Call<List<MechanicDto>> getServices(@Path("id") int id);

    @GET("mechanics/{id}/orders")
    Call<List<OrderResponse>> getOrders(@Path("id") int id);

}
