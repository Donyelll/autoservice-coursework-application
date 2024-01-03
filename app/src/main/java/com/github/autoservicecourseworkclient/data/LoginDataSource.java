package com.github.autoservicecourseworkclient.data;


import com.github.autoservicecourseworkclient.data.model.LoggedInUser;
import com.github.autoservicecourseworkclient.logic.controller.UserCredentialsController;
import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsRequest;
import com.github.autoservicecourseworkclient.logic.dto.UserCredentialsResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        UserCredentialsRequest request = new UserCredentialsRequest(username, password);
        System.err.println(username + " " + password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.5:8080/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        UserCredentialsController userCredentialsController = retrofit.create(UserCredentialsController.class);
        try {
            UserCredentialsResponse currentUser = new UserCredentialsResponse();
            Thread thread = new Thread(() -> {
                Call<UserCredentialsResponse> credentials = userCredentialsController.getCustomerCredentials(request);

                Response<UserCredentialsResponse> response = null;
                try {
                    response = credentials.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (response.body() == null) {
                    credentials = userCredentialsController.getMechanicCredentials(request);
                    try {
                        response = credentials.execute();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (response.body() == null) {
                    } else {
                        currentUser.setId(response.body().getId());
                        currentUser.setLogin(response.body().getLogin());
                        currentUser.setName(response.body().getName());
                        currentUser.setSurname(response.body().getSurname());
                        currentUser.setRole("Механик");
                    }
                } else {
                    currentUser.setId(response.body().getId());
                    currentUser.setLogin(response.body().getLogin());
                    currentUser.setName(response.body().getName());
                    currentUser.setSurname(response.body().getSurname());
                    currentUser.setRole("Клиент");


                }
            });

            thread.start();
            Thread.sleep(500);
            if (currentUser.getId() != 0) {
                LoggedInUser fakeUser =
                        new LoggedInUser(String.valueOf(currentUser.getId()), currentUser.getName(), currentUser.getSurname(), currentUser.getLogin(), currentUser.getRole());
                return new Result.Success<>(fakeUser);
            } else {
                return new Result.Error(new IOException("Error logging in"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {

    }


}