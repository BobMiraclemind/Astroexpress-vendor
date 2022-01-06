package com.bob.astroexpress_vendor.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.bob.astroexpress_vendor.adapter.UserListAdapter;
import com.bob.astroexpress_vendor.databinding.ActivityChatListBinding;
import com.bob.astroexpress_vendor.datamodel.UsersModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    ActivityChatListBinding binding;
    UserListAdapter adapter;
    ArrayList<UsersModel> dataHolder;
    DatabaseReference databaseReference;
    String userUid,uid;
    String username="",key="", gender="", dob, tob, pob;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Available Users");

        dataHolder = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getInstance().getCurrentUser();
        if (mUser != null){
            uid = mUser.getUid();
        }
        databaseReference = firebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    userUid = dataSnapshot.getKey();
                    if (snapshot.child(userUid.trim()).child("Request").child(uid).getValue() !=null &&
                            snapshot.child(userUid.trim()).child("Request").child(uid).child("user_status").getValue() !=null) {


                        if (snapshot.child(userUid.trim()).child("Request").child(uid).child("user_status").getValue().equals("active")) {


                            if (snapshot.child(userUid.trim()).child("name").getValue() != null &&
                                    snapshot.child(userUid.trim()).child("gen").getValue() != null &&
                                    snapshot.child(userUid.trim()).child("dob").getValue() != null &&
                                    snapshot.child(userUid.trim()).child("tob").getValue() != null &&
                                    snapshot.child(userUid.trim()).child("pob").getValue() != null) {

                                username = snapshot.child(userUid.trim()).child("name").getValue(String.class);
                                gender = snapshot.child(userUid.trim()).child("gen").getValue(String.class);
                                dob = snapshot.child(userUid.trim()).child("dob").getValue(String.class);
                                tob = snapshot.child(userUid.trim()).child("tob").getValue(String.class);
                                pob = snapshot.child(userUid.trim()).child("pob").getValue(String.class);

                                //&& status.equals("true")

                                if (username != "" && gender != "" && dob != "" && tob != "" && pob != "") {
                                    UsersModel ob1 = new UsersModel(username,uid,dob,tob,pob,gender);
                                    dataHolder.add(ob1);
                                    adapter.notifyDataSetChanged();
                                    //recyclerView.setAdapter(adapter);
                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new UserListAdapter(getApplicationContext(),dataHolder);
        binding.recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recview.setAdapter(adapter);

    }
}