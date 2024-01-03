package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.CustomerDto;
import com.github.autoservicecourseworkclient.logic.dto.InfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface InfoController {

    @GET("info/")
    Call<InfoResponse> getInfo();
}
