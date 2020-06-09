package com.priyank_py.i2e1task.api;

import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
//    @GET("users")
//    Observable<DataResponse> getAllUsers(@Query("offset") int offset, @Query("limit") int limit);

    @GET("users")
    Call<DataResponse> getAllUsers(@Query("offset") int offset, @Query("limit") int limit);
}
