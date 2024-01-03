package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.OrderRequest;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrdersController {

    @POST("orders/")
    Call<OrderResponse> createOrder(@Body OrderRequest request);

    @DELETE("orders/{id}")
    Call<Integer> deleteOrder(@Path("id") Integer id);

}
