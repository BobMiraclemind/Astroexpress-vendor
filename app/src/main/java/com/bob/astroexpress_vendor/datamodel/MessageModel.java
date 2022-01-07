package com.bob.astroexpress_vendor.datamodel;

public class MessageModel {
    public String message,sender,senderId,timestamp;

    public MessageModel() {
    }

    public MessageModel(String message, String sender, String senderId, String timestamp) {
        this.message = message;
        this.sender = sender;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
