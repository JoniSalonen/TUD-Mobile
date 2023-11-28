package com.example.passwordmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WebModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private LoginDetailsRepo repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<LoginDetailsModal>> allDetails;

    // constructor for our view modal.
    public WebModal(@NonNull Application application) {
        super(application);
        repository = new LoginDetailsRepo(application);
        allDetails = repository.getAllDetails();
    }

    // below method is use to insert the data to our repository.
    public void insert(LoginDetailsModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(LoginDetailsModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(LoginDetailsModal model) {
        repository.delete(model);
    }

    // below method is to delete all the details in our list.
    public void deleteAllDetails() {
        repository.deleteAllDetails();
    }

    // below method is to get all the details in our list.
    public LiveData<List<LoginDetailsModal>> getAllDetails() {
        return allDetails;
    }
}
