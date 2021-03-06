package com.priyank_py.i2e1task.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public MutableLiveData<DataResponse> getAllUsers(int offset, int page) {
        return userRepository.getAllUsers(offset, page);
    }
}
