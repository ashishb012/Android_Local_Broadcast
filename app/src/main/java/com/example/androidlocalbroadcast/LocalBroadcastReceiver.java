package com.example.androidlocalbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LocalBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "LocalBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Local Broadcast Received");
        if (intent.getAction() != null && intent.getAction().equals("com.example.androidlocalbroadcast.MY_LOCAL_BROADCAST")) {
            String localStringExtra = intent.getStringExtra(MainActivity.LOCAL_STRING_EXTRA);

            String message = "Local Broadcast: String = " + localStringExtra;
            Log.d(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}