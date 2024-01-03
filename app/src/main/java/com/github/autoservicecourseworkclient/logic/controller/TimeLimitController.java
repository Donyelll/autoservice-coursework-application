package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.MaterialsResponse;
import com.github.autoservicecourseworkclient.logic.dto.TimeLimitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimeLimitController {

    @GET("time/")
    Call<List<TimeLimitResponse>> getAll();

}
