package com.example.androidlocalbroadcast;

import android.content.ComponentName;
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
    private static final String APP1_PACKAGE_NAME = "com.example.androidbroadcastreciever"; // Replace with App 1's package name
    private static final String APP1_RECEIVER_CLASS = "com.example.androidbroadcastreciever.CustomBroadcastReceiver"; // Replace with App 1's receiver class name
    private LocalBroadcastReceiver localBroadcastReceiver;
    private App1Receiver app1Receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Register the LocalBroadcastReceiver
        localBroadcastReceiver = new LocalBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver,
                new android.content.IntentFilter(LOCAL_INTENT_ACTION));
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
        // Register receiver for app1 broadcast
        app1Receiver = new App1Receiver();
        IntentFilter intentFilter = new IntentFilter("com.example.androidbroadcastreciever.MY_CUSTOM_INTENT");
        registerReceiver(app1Receiver, intentFilter, Context.RECEIVER_EXPORTED);
    }

    private void sendGlobalBroadcast() {
        Log.d(TAG, "Sending Global Broadcast");
        Intent globalIntent = new Intent(GLOBAL_INTENT_ACTION);
        globalIntent.putExtra(STRING_EXTRA, "Hello Global!");
        globalIntent.putExtra(INT_EXTRA, 123);
        // Explicitly set the component name to target App 1's receiver
        ComponentName componentName = new ComponentName(APP1_PACKAGE_NAME, APP1_RECEIVER_CLASS);
        globalIntent.setComponent(componentName);
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
        unregisterReceiver(app1Receiver);
        super.onDestroy();
    }
}
