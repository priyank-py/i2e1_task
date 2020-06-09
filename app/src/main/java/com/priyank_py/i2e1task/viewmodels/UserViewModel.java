package com.priyank_py.i2e1task.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.MainResponse;
import com.priyank_py.i2e1task.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<DataResponse> userResponse = new MutableLiveData<>();

    public UserViewModel(Application application) {
        super(application);
//        userResponse = UserRepository.getInstance().getUserList();
        userRepository = new UserRepository(application);
    }

//    public void subscribeToUsers() {
//        userResponse = userRepository.getUserList();
//    }

    public LiveData<DataResponse> getAllUsers() {
        userResponse = userRepository.getAllUsers();
        return userResponse;
    }
}
