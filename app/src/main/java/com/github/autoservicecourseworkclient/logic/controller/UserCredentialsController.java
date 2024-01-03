package com.github.autoservicecourseworkclient.logic.controller;

import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsRequest;
import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserCredentialsController {

    @POST("customers/login")
    Call<UserCredentialsResponse> getCustomerCredentials(@Body UserCredentialsRequest credentials);

    @POST("mechanics/login")
    Call<UserCredentialsResponse> getMechanicCredentials(@Body UserCredentialsRequest credentials);

}
