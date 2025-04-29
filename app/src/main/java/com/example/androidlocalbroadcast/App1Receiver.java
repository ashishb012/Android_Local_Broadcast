package com.example.androidlocalbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class App1Receiver extends BroadcastReceiver {

    private static final String TAG = "App1Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast from App1 Received");
        if (intent.getAction() != null && intent.getAction().equals("com.example.androidbroadcastreciever.MY_CUSTOM_INTENT")) {
            Toast.makeText(context, "App1 Custom Broadcast Received!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "App1 Custom Broadcast Received! ");
        }
    }
}