package com.github.autoservicecourseworkclient.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.autoservicecourseworkclient.data.model.LoggedInUser;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> credentials = new MutableLiveData<>();
    private final MutableLiveData<LoggedInUser> loggedUser = new MutableLiveData<>();

    public ProfileViewModel() {
    }

    public MutableLiveData<LoggedInUser> getUser() {
        return loggedUser;
    }
    public void setUser(LoggedInUser user){
        loggedUser.setValue(user);
    }

    public LiveData<String> getText() {
        return credentials;
    }

    public void setText (String value){
        credentials.setValue(value);
    }
}