package com.priyank_py.i2e1task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.priyank_py.i2e1task.adapters.UsersAdapter;
import com.priyank_py.i2e1task.api.UserApi;
import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.MainResponse;
import com.priyank_py.i2e1task.entities.User;
import com.priyank_py.i2e1task.viewmodels.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private ProgressBar progressBar;
    private List<DataResponse.DataBean.UsersBean> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_load_data);
        usersRecyclerView = findViewById(R.id.rv_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        usersRecyclerView.setLayoutManager(linearLayoutManager);
        usersRecyclerView.setHasFixedSize(false);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, it -> {
            users = it.getData().getUsers();
            UsersAdapter usersAdapter = new UsersAdapter();
            usersAdapter.setUsers(users, getApplicationContext());
            usersRecyclerView.setAdapter(usersAdapter);
            progressBar.setVisibility(View.GONE);
        });
    }
}