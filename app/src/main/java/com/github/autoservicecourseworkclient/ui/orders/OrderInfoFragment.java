package com.github.autoservicecourseworkclient.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.autoservicecourseworkclient.R;
import com.github.autoservicecourseworkclient.databinding.FragmentOrderInfoBinding;
import com.github.autoservicecourseworkclient.logic.controller.OrdersController;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OrderInfoFragment extends Fragment {

    private OrdersViewModel ordersViewModel;
    private FragmentOrderInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrderInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        TextView textView =  view.findViewById(R.id.order_info_id);
        textView.setText(ordersViewModel.getCurrentOrder().getValue().toString());
        Button button = binding.cancelOrderButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://172.20.10.5:8080/api/v1/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                OrdersController ordersController = retrofit.create(OrdersController.class);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call<Integer> ordersCall = ordersController.deleteOrder(ordersViewModel.getCurrentOrder().getValue().getId());
                        try {
                            ordersCall.execute().body();
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

                Toast.makeText(requireContext(),"Заказ отменён", Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(OrderInfoFragment.super.getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_orders);
            }
        });
    }

}
