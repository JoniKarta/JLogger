package com.example.jloggerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.jloggerlib.LoggerUI;
import com.example.jloggerlib.model.Logger;

import java.util.Date;

public class MainActivity extends LoggerUI {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Delete all logs from the system
        getLoggerViewModel().deleteAll();

        // Create new logs
        new Thread(() -> {
            int i = 5;
            while (i-- > 0) {
                try {
                    Thread.sleep(1000);
                    getLoggerViewModel().insert(new Logger(Logger.DEBUG, "Error " + i, new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int i = 3;
            while (i-- > 0) {
                try {
                    Thread.sleep(1000);
                    getLoggerViewModel().insert(new Logger(Logger.INFO, "Info " + i, new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int i = 2;
            while (i-- > 0) {
                try {
                    Thread.sleep(1000);
                    getLoggerViewModel().insert(new Logger(Logger.OTHER, "Hello World " + i, new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Observe all logs in the system
        getLoggerViewModel().getAllLogs().observe(this, loggers -> {
            Log.i(TAG, "onCreate: " + loggers);
        });

        // Observe number of logs in the system
        getLoggerViewModel().loggerCount().observe(this, count -> {
            Log.i(TAG, "onCreate: " + count);
        });

    }
}