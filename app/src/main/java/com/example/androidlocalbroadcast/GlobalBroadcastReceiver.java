package com.example.androidlocalbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class GlobalBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "GlobalBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Global Broadcast Received");
        if (intent.getAction() != null && intent.getAction().equals("com.example.androidlocalbroadcast.MY_GLOBAL_BROADCAST")) {
            String stringExtra = intent.getStringExtra(MainActivity.STRING_EXTRA);
            int intExtra = intent.getIntExtra(MainActivity.INT_EXTRA, 0);

            String message = "Global Broadcast: String = " + stringExtra + ", Int = " + intExtra;
            Log.d(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}