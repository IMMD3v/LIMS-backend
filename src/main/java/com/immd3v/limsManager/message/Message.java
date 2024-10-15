package com.immd3v.limsManager.message;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class Message {
    private String message;
    public Message(String message) {
        this.message = message;
    }
    // Getters y setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
