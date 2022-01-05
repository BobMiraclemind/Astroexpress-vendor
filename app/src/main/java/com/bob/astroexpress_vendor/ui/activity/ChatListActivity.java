package com.bob.astroexpress_vendor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.bob.astroexpress_vendor.adapter.UserListAdapter;
import com.bob.astroexpress_vendor.databinding.ActivityChatListBinding;
import com.bob.astroexpress_vendor.datamodel.UsersModel;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    ActivityChatListBinding binding;
    UserListAdapter adapter;
    ArrayList<UsersModel> dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Available Users");

        dataHolder = new ArrayList<>();

        adapter = new UserListAdapter(getApplicationContext(),dataHolder);
        binding.recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}