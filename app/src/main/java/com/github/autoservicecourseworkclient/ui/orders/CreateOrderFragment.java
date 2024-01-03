package com.github.autoservicecourseworkclient.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.autoservicecourseworkclient.R;
import com.github.autoservicecourseworkclient.databinding.FragmentCreateOrderBinding;
import com.github.autoservicecourseworkclient.databinding.FragmentOrderInfoBinding;
import com.github.autoservicecourseworkclient.logic.controller.CustomerController;
import com.github.autoservicecourseworkclient.logic.controller.MaterialsController;
import com.github.autoservicecourseworkclient.logic.controller.MechanicController;
import com.github.autoservicecourseworkclient.logic.controller.OrdersController;
import com.github.autoservicecourseworkclient.logic.controller.ServicesTypeController;
import com.github.autoservicecourseworkclient.logic.controller.TimeLimitController;
import com.github.autoservicecourseworkclient.logic.dto.MaterialsResponse;
import com.github.autoservicecourseworkclient.logic.dto.MechanicDto;
import com.github.autoservicecourseworkclient.logic.dto.OrderRequest;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;
import com.github.autoservicecourseworkclient.logic.dto.ServiceTypeResponse;
import com.github.autoservicecourseworkclient.logic.dto.TimeLimitResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CreateOrderFragment extends Fragment {
    private FragmentCreateOrderBinding binding;
    private int basePrice = 0;
    private double timeLimitCoef = 0;
    private double materialsCoef = 0;
    private OrdersViewModel ordersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.total_price);
        textView.setText(String.valueOf(Math.round(basePrice * materialsCoef * timeLimitCoef)));

        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);

        List<ServiceTypeResponse> services = new ArrayList<>();
        List<MechanicDto> mechanics = new ArrayList<>();
        List<MaterialsResponse> materials = new ArrayList<>();
        List<TimeLimitResponse> timeLimit = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.4:8080/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ServicesTypeController servicesTypeController = retrofit.create(ServicesTypeController.class);
        MaterialsController materialsController = retrofit.create(MaterialsController.class);
        TimeLimitController timeLimitController = retrofit.create(TimeLimitController.class);
        MechanicController mechanicController = retrofit.create(MechanicController.class);
        OrdersController ordersController = retrofit.create(OrdersController.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<ServiceTypeResponse>> servicesCall = servicesTypeController.getAll();
                Call<List<MaterialsResponse>> materialsCall = materialsController.getAll();
                Call<List<TimeLimitResponse>> timeLimitCall = timeLimitController.getAll();
                try {
                    services.addAll(servicesCall.execute().body());
                    materials.addAll(materialsCall.execute().body());
                    timeLimit.addAll(timeLimitCall.execute().body());
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

        OrderRequest request = new OrderRequest();

        ArrayAdapter<MechanicDto> mechanicAdapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, R.id.order_id,new ArrayList<>());
        ArrayAdapter<ServiceTypeResponse> serviceAdapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, R.id.order_id,services);
        ArrayAdapter<MaterialsResponse> materialsAdapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, R.id.order_id,materials);
        ArrayAdapter<TimeLimitResponse> timeAdapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, R.id.order_id,timeLimit);

        Spinner serviceSpinner = view.findViewById(R.id.service_spinner);
        Spinner mechanicSpinner = view.findViewById(R.id.mechanic_spinner);
        Spinner materialsSpinner = view.findViewById(R.id.materials_spinner);
        Spinner timeSpinner = view.findViewById(R.id.time_spinner);

        serviceSpinner.setAdapter(serviceAdapter);
        mechanicSpinner.setAdapter(mechanicAdapter);
        materialsSpinner.setAdapter(materialsAdapter);
        timeSpinner.setAdapter(timeAdapter);
        serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Thread mechanicThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call<List<MechanicDto>> mechanicCall = servicesTypeController.getMechanics(((ServiceTypeResponse)parent.getSelectedItem()).getId());
                        try {
                            mechanics.clear();
                            mechanics.addAll(mechanicCall.execute().body());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                mechanicThread.start();
                try {
                    Thread.sleep(300);
                } catch (Exception e){

                }
                request.setServiceId(((ServiceTypeResponse)parent.getSelectedItem()).getId());
                basePrice = ((ServiceTypeResponse)parent.getSelectedItem()).getBasePrice();
                updatePrice(textView);
                mechanicAdapter.clear();
                mechanicAdapter.addAll(mechanics);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mechanicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                request.setMechanicId(((MechanicDto)parent.getSelectedItem()).getId());
                updatePrice(textView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        materialsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                request.setMaterialsId(((MaterialsResponse)parent.getSelectedItem()).getId());
                materialsCoef = ((MaterialsResponse)parent.getSelectedItem()).getPriceCoef();
                updatePrice(textView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                request.setTimeLimitId(((TimeLimitResponse)parent.getSelectedItem()).getId());
                timeLimitCoef = ((TimeLimitResponse)parent.getSelectedItem()).getPriceCoef();
                updatePrice(textView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = binding.createOrderButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((TextView)view.findViewById(R.id.total_price)).getText().equals("0")){
                    ordersViewModel.createOrder(request);
                    NavController navController = Navigation.findNavController(CreateOrderFragment.super.getActivity(), R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.order_info_fragment);
                }
            }
        });

    }

    private void updatePrice (TextView view) {
        view.setText(String.valueOf(Math.round(basePrice * materialsCoef * timeLimitCoef)));
    }


}
