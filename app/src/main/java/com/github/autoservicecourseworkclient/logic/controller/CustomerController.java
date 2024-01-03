package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;
import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CustomerController {

    @GET("customers/")
    Call<List<UserCredentialsResponse>> getCredentials();

    @GET("customers/{id}/orders")
    Call<List<OrderResponse>> getOrders(@Path("id") int id);

}
