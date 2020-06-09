package com.priyank_py.i2e1task.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.priyank_py.i2e1task.R;
import com.priyank_py.i2e1task.entities.DataResponse;
import com.priyank_py.i2e1task.entities.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<DataResponse.DataBean.UsersBean> users;
    private Context mContext;


    public void setUsers(List<DataResponse.DataBean.UsersBean> users, Context context) {
        this.users = users;
        this.mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View userList = layoutInflater.inflate(R.layout.user_layout, parent, false);
        return new UserViewHolder(userList);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DataResponse.DataBean.UsersBean currentUser = users.get(position);

        holder.userName.setText(currentUser.getName());
        Picasso.get().load(currentUser.getImage()).into(holder.userIcon);

        List<String> images = currentUser.getItems();

        if (images.size() % 2 == 1) {
            holder.cardView.setVisibility(View.VISIBLE);
            Picasso.get().load(images.get(0)).into(holder.oddImage);
            images.remove(0);
        } else {
            holder.cardView.setVisibility(View.GONE);
        }
        if (images.size() > 0) {
            holder.userItems.setVisibility(View.VISIBLE);
            ItemAdapter itemAdapter = new ItemAdapter();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
            holder.userItems.setLayoutManager(gridLayoutManager);
            holder.userItems.setAdapter(itemAdapter);
            itemAdapter.setItems(images);
            holder.userItems.setHasFixedSize(false);
            itemAdapter.notifyDataSetChanged();
        } else {
            holder.userItems.setVisibility(View.GONE);
        }
    }

    public void addUsers(List<DataResponse.DataBean.UsersBean> newUsers) {
        for (DataResponse.DataBean.UsersBean user : newUsers) {
            this.users.add(user);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userIcon;
        private TextView userName;
        private RecyclerView userItems;
        private CardView cardView;
        private ImageView oddImage;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userIcon = itemView.findViewById(R.id.iv_user_icon);
            userName = itemView.findViewById(R.id.tv_user_name);
            userItems = itemView.findViewById(R.id.rv_items);
            cardView = itemView.findViewById(R.id.cv_odd);
            oddImage = itemView.findViewById(R.id.iv_image_wide);
        }
    }
}
