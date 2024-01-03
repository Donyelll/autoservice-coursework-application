package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.MaterialsResponse;
import com.github.autoservicecourseworkclient.logic.dto.MechanicDto;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;
import com.github.autoservicecourseworkclient.logic.dto.ServiceTypeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicesTypeController {

    @GET("services/")
    Call<List<ServiceTypeResponse>> getAll();

    @GET("services/{id}/mechanics")
    Call<List<MechanicDto>> getMechanics(@Path("id") int id);

}
