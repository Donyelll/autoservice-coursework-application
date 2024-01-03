package com.github.autoservicecourseworkclient.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.autoservicecourseworkclient.R;
import com.github.autoservicecourseworkclient.data.model.LoggedInUser;
import com.github.autoservicecourseworkclient.databinding.FragmentOrdersBinding;
import com.github.autoservicecourseworkclient.databinding.FragmentProfileBinding;
import com.github.autoservicecourseworkclient.ui.login.LoginFragment;
import com.github.autoservicecourseworkclient.ui.orders.OrdersViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private OrdersViewModel ordersViewModel;

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        LoggedInUser user = profileViewModel.getUser().getValue();
        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        ordersViewModel.setUser(user);
        final TextView loginTextView = view.findViewById(R.id.text_profile_login);
        final TextView nameTextView = view.findViewById(R.id.text_profile_name);
        final TextView surnameTextView = view.findViewById(R.id.text_profile_surname);
        loginTextView.setText(user.getLogin());
        nameTextView.setText(user.getName());
        surnameTextView.setText(user.getSurname());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}