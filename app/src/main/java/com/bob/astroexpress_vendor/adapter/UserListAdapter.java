package com.bob.astroexpress_vendor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bob.astroexpress_vendor.R;
import com.bob.astroexpress_vendor.databinding.SingleUserItemBinding;
import com.bob.astroexpress_vendor.datamodel.UsersModel;
import com.bob.astroexpress_vendor.ui.activity.ChatsActivity;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    Context context;
    ArrayList<UsersModel> dataHolder;

    public UserListAdapter(Context context, ArrayList<UsersModel> dataHolder) {
        this.context = context;
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final UsersModel usersModel = dataHolder.get(position);
        holder.binding.name.setText(usersModel.getName());
        holder.binding.gender.setText(usersModel.getGender());
        holder.binding.dob.setText(usersModel.getDob());
        holder.binding.tob.setText(usersModel.getTob());
        holder.binding.pob.setText(usersModel.getPob());

        holder.binding.gotoChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatsActivity.class);
                intent.putExtra("username",usersModel.getName());
                intent.putExtra("user_uid",usersModel.getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SingleUserItemBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleUserItemBinding.bind(itemView);
        }
    }
}
