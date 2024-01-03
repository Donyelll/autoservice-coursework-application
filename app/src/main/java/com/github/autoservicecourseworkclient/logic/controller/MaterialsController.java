package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.MaterialsResponse;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MaterialsController {

    @GET("materials/")
    Call<List<MaterialsResponse>> getAll();
}
