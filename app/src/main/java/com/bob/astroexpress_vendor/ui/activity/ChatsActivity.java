package com.bob.astroexpress_vendor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bob.astroexpress_vendor.databinding.ActivityChatsBinding;

public class ChatsActivity extends AppCompatActivity {

    ActivityChatsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}