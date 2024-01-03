package com.github.autoservicecourseworkclient.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.github.autoservicecourseworkclient.data.LoginDataSource;
import com.github.autoservicecourseworkclient.data.LoginRepository;
import com.github.autoservicecourseworkclient.ui.login.LoginViewModel;
import com.github.autoservicecourseworkclient.ui.profile.ProfileViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}