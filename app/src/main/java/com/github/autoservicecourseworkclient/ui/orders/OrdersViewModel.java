package com.github.autoservicecourseworkclient.ui.orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.autoservicecourseworkclient.data.model.LoggedInUser;
import com.github.autoservicecourseworkclient.logic.controller.CustomerController;
import com.github.autoservicecourseworkclient.logic.controller.MechanicController;
import com.github.autoservicecourseworkclient.logic.controller.OrdersController;
import com.github.autoservicecourseworkclient.logic.dto.OrderRequest;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OrdersViewModel extends ViewModel {

    private final MutableLiveData<LoggedInUser> loggedUser = new MutableLiveData<>();
    private final MutableLiveData<List<OrderResponse>> orders = new MutableLiveData<>();
    private final MutableLiveData<OrderResponse> currentOrder = new MutableLiveData<>();
    private final MutableLiveData<OrderRequest> orderRequest = new MutableLiveData<>();

    public MutableLiveData<LoggedInUser> getUser() {
        return loggedUser;
    }
    public void setUser(LoggedInUser user){
        loggedUser.setValue(user);
    }

    public MutableLiveData<List<OrderResponse>> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> newOrders){
        orders.setValue(newOrders);
    }

    public MutableLiveData<OrderResponse> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderResponse orderResponse){
        currentOrder.setValue(orderResponse);
    }

    public void postCurrentOrder(OrderResponse orderResponse){
        currentOrder.postValue(orderResponse);
    }

    public OrdersViewModel() {
    }

    public MutableLiveData<OrderRequest> getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest request){
        orderRequest.setValue(request);
    }

    public void getOrders(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.5:8080/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        CustomerController customerController = retrofit.create(CustomerController.class);
        MechanicController mechanicController = retrofit.create(MechanicController.class);
        List<OrderResponse> currentOrders = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<OrderResponse>> call = mechanicController.getOrders(id);
                if(getUser().getValue().getRole().equals("Клиент")) {
                    call = customerController.getOrders(id);
                }
                try {
                    currentOrders.addAll(call.execute().body());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (Exception e){

        }
        orders.setValue(currentOrders);

    }

    public void createOrder(OrderRequest request){
        request.setCustomerId(Integer.valueOf(getUser().getValue().getUserId()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.5:8080/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        OrdersController ordersController = retrofit.create(OrdersController.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<OrderResponse> ordersCall = ordersController.createOrder(request);
                try {
                    OrderResponse response = ordersCall.execute().body();
                    postCurrentOrder(response);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(300);
        } catch (Exception e){
        }
    }
}