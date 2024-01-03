package com.github.autoservicecourseworkclient.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.autoservicecourseworkclient.R;
import com.github.autoservicecourseworkclient.data.model.LoggedInUser;
import com.github.autoservicecourseworkclient.databinding.FragmentOrdersBinding;
import com.github.autoservicecourseworkclient.logic.dto.OrderResponse;
import com.github.autoservicecourseworkclient.ui.login.LoginFragment;
import com.github.autoservicecourseworkclient.ui.profile.ProfileViewModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrdersFragment extends Fragment {

    private OrdersViewModel ordersViewModel;
    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        LoggedInUser user = ordersViewModel.getUser().getValue();
        ordersViewModel.getOrders(Integer.valueOf(user.getUserId()));
        List<OrderResponse> orders = ordersViewModel.getOrders().getValue();
        ListView listView = view.findViewById(R.id.orders_list);
        ArrayAdapter<OrderResponse> adapter = new ArrayAdapter<>(requireContext(),R.layout.list_item,R.id.order_id, orders);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ordersViewModel.setCurrentOrder((OrderResponse) adapterView.getItemAtPosition(i));
                NavController navController = Navigation.findNavController(OrdersFragment.super.getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.order_info_fragment);
                Toast.makeText(requireContext(),((TextView)view.findViewById(R.id.order_id)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}