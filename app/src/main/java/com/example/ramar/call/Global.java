package com.example.ramar.call;

import android.app.Application;

public class Global extends Application {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
