package com.bob.astroexpress_vendor.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bob.astroexpress_vendor.adapter.MessageAdapter;
import com.bob.astroexpress_vendor.databinding.ActivityChatBinding;
import com.bob.astroexpress_vendor.datamodel.MessageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";

    ActivityChatBinding binding;
    String receiverUid,senderName;

    FirebaseAuth auth;
    FirebaseDatabase database;

    ArrayList<MessageModel> dataHolder;
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("username");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        final String senderUid = auth.getUid();
        receiverUid = getIntent().getStringExtra("user_uid");
        final String receiverRoom = receiverUid+senderUid;

        dataHolder = new ArrayList<>();

        database.getReference().child("chats").child(receiverRoom).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataHolder.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MessageModel message = new MessageModel(String.valueOf(dataSnapshot.child("message").getValue()),
                            String.valueOf(dataSnapshot.child("sender").getValue()),
                            String.valueOf(dataSnapshot.child("senderId").getValue()),
                            String.valueOf(dataSnapshot.child("timestamp").getValue()));
                    dataHolder.add(message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new MessageAdapter(getApplicationContext(),dataHolder);
        binding.messageView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.messageView.setAdapter(adapter);

        database.getReference().child("Astrologers").child(senderUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                senderName = String.valueOf(snapshot.child("displayname").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.messageBox.getText().toString().isEmpty()){
                    Date date = new Date();
                    String message = binding.messageBox.getText().toString();
                    final MessageModel model = new MessageModel(message,senderName,senderUid,String.valueOf(date.getTime()));
                    binding.messageBox.setText("");

                    database.getReference().child("chats")
                            .child(receiverRoom)
                            .child("messages").push()
                            .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}