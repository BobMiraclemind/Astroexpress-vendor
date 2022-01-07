package com.bob.astroexpress_vendor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bob.astroexpress_vendor.R;
import com.bob.astroexpress_vendor.databinding.ItemReceiveBinding;
import com.bob.astroexpress_vendor.databinding.ItemSentBinding;
import com.bob.astroexpress_vendor.datamodel.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<MessageModel> dataHolder;

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public MessageAdapter(Context context, ArrayList<MessageModel> dataHolder) {
        this.context = context;
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new OutgoingViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new IncomingViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageModel message = dataHolder.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message = dataHolder.get(position);
        if (holder.getClass() == OutgoingViewHolder.class){
            OutgoingViewHolder outgoingViewHolder = (OutgoingViewHolder) holder;
            outgoingViewHolder.binding.msgSent.setText(message.getMessage());
        }else {
            IncomingViewHolder incomingViewHolder = (IncomingViewHolder) holder;
            incomingViewHolder.binding.msgReceive.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class OutgoingViewHolder extends RecyclerView.ViewHolder{

        ItemSentBinding binding;

        public OutgoingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSentBinding.bind(itemView);
        }
    }

    public class IncomingViewHolder extends RecyclerView.ViewHolder {

        ItemReceiveBinding binding;

        public IncomingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveBinding.bind(itemView);
        }
    }
}
