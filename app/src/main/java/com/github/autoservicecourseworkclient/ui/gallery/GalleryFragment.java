package com.github.autoservicecourseworkclient.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.autoservicecourseworkclient.databinding.FragmentGalleryBinding;
import com.github.autoservicecourseworkclient.logic.controller.CustomerController;
import com.github.autoservicecourseworkclient.logic.controller.InfoController;
import com.github.autoservicecourseworkclient.logic.controller.MechanicController;
import com.github.autoservicecourseworkclient.logic.dto.InfoResponse;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView textView = binding.textGallery;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.4:8080/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        InfoController infoController = retrofit.create(InfoController.class);
        StringBuilder applicationInfo = new StringBuilder();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<InfoResponse> call = infoController.getInfo();
                try {
                    applicationInfo.append(call.execute().body().getMessage());
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
        textView.setText(applicationInfo.toString());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}