package com.bob.astroexpress_vendor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bob.astroexpress_vendor.databinding.ActivityChatListBinding;

public class ChatListActivity extends AppCompatActivity {

    ActivityChatListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Available Users");

    }
}