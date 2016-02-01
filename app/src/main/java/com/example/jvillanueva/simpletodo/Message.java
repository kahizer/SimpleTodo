package com.example.jvillanueva.simpletodo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kahizer on 2/1/2016.
 */
public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
