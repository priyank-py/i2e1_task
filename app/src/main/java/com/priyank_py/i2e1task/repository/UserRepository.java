package com.priyank_py.i2e1task.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.priyank_py.i2e1task.api.UserApi;
import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.MainResponse;
import com.priyank_py.i2e1task.network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserApi userApi;
    private static UserRepository mUserRepository;

    public UserRepository(Application application) {
        userApi = NetworkUtils.createService(UserApi.class);
    }

//    public static UserRepository getInstance() {
//        if (mUserRepository == null) {
//            mUserRepository = new UserRepository();
//        }
//        return mUserRepository;
//    }

//    private static void subscribeToUsers() {
//        userApi.getAllUsers(10, 10)
//                .subscribeOn(Schedulers.io())
//                .doOnError(Throwable::printStackTrace)
//                .subscribe(new Observer<DataResponse>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(DataResponse userResponse) {
//                        allUsers.postValue(userResponse);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public LiveData<DataResponse> getUserList() {
//        subscribeToUsers();
//        return allUsers;
//    }

    public MutableLiveData<DataResponse> getAllUsers() {
        final MutableLiveData<DataResponse> allUsers = new MutableLiveData<>();
        userApi.getAllUsers(10, 10).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    allUsers.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                allUsers.setValue(null);
            }
        });
        return allUsers;
    }
}
