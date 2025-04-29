package com.example.androidlocalbroadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String GLOBAL_INTENT_ACTION = "com.example.androidlocalbroadcast.MY_GLOBAL_BROADCAST";
    private static final String LOCAL_INTENT_ACTION = "com.example.androidlocalbroadcast.MY_LOCAL_BROADCAST";
    public static final String STRING_EXTRA = "stringExtra";
    public static final String INT_EXTRA = "intExtra";
    public static final String LOCAL_STRING_EXTRA = "localStringExtra";
    private LocalBroadcastReceiver localBroadcastReceiver;
    private GlobalBroadcastReceiver globalBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register the LocalBroadcastReceiver
        localBroadcastReceiver = new LocalBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver,
                new IntentFilter(LOCAL_INTENT_ACTION));
        globalBroadcastReceiver = new GlobalBroadcastReceiver();
        registerReceiver(globalBroadcastReceiver, new IntentFilter(GLOBAL_INTENT_ACTION), Context.RECEIVER_EXPORTED);

        Button sendGlobalBroadcastButton = findViewById(R.id.sendGlobalBroadcastButton);
        sendGlobalBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGlobalBroadcast();
            }
        });

        Button sendLocalBroadcastButton = findViewById(R.id.sendLocalBroadcastButton);
        sendLocalBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocalBroadcast();
            }
        });
    }

    private void sendGlobalBroadcast() {
        Log.d(TAG, "Sending Global Broadcast");
        Intent globalIntent = new Intent(GLOBAL_INTENT_ACTION);
        globalIntent.putExtra(STRING_EXTRA, "Hello Global!");
        globalIntent.putExtra(INT_EXTRA, 123);
        sendBroadcast(globalIntent);
    }

    private void sendLocalBroadcast() {
        Log.d(TAG, "Sending Local Broadcast");
        Intent localIntent = new Intent(LOCAL_INTENT_ACTION);
        localIntent.putExtra(LOCAL_STRING_EXTRA, "Hello Local!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
    @Override
    protected void onDestroy() {
        // Unregister the receiver in onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiver);
        unregisterReceiver(globalBroadcastReceiver);
        super.onDestroy();
    }
}