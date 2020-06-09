package com.priyank_py.i2e1task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.priyank_py.i2e1task.Pagination.EndlessRecyclerViewScrollListener;
import com.priyank_py.i2e1task.adapters.UsersAdapter;
import com.priyank_py.i2e1task.api.UserApi;
import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.MainResponse;
import com.priyank_py.i2e1task.entities.User;
import com.priyank_py.i2e1task.viewmodels.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private ProgressBar progressBar, loadMore;
    public int offset = 0;
    public int limit = 10;
    UserViewModel userViewModel;
    UsersAdapter usersAdapter;
    private List<DataResponse.DataBean.UsersBean> users;
    MutableLiveData<DataResponse> responseData;
    EndlessRecyclerViewScrollListener scrollListener;
    boolean hasMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_load_data);
        usersRecyclerView = findViewById(R.id.rv_users);
        loadMore = findViewById(R.id.pb_load_more);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        usersRecyclerView.setLayoutManager(linearLayoutManager);
        usersRecyclerView.setHasFixedSize(false);
        usersAdapter = new UsersAdapter();

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        responseData = userViewModel.getAllUsers(offset, limit);
        responseData.observe(this, it -> {
            users = it.getData().getUsers();
            hasMore = it.getData().isHas_more();
            usersAdapter.setUsers(users, getApplicationContext());
            usersRecyclerView.setAdapter(usersAdapter);
            progressBar.setVisibility(View.GONE);
        });

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMore.setVisibility(View.VISIBLE);
                offset += limit;
                userViewModel.getAllUsers(offset, limit).observe(MainActivity.this, it -> {
                    users.addAll(it.getData().getUsers());
                    hasMore = it.getData().isHas_more();
                    usersAdapter.notifyDataSetChanged();
                    usersAdapter.notifyItemInserted(users.size()-1);
                });
                if (!hasMore) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.noMoreData), Toast.LENGTH_SHORT).show();
                }
//                view.post(() -> usersAdapter.notifyItemInserted(users.size()-1));
                loadMore.setVisibility(View.GONE);
            }
        };
        usersRecyclerView.addOnScrollListener(scrollListener);
    }
}