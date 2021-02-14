package com.example.jloggerlib;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.jloggerlib.viewmodel.LoggerViewModel;


public final class LoggerManager {

    private static LoggerManager loggerManager;
    private final LoggerViewModel loggerViewModel;

    private LoggerManager(Context context) {
        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory((Application)context).create(LoggerViewModel.class);
    }

    public synchronized static LoggerManager getInstance(Context context) {
        if (loggerManager == null) {
            loggerManager = new LoggerManager(context);
        }
        return loggerManager;
    }

    public LoggerViewModel getLoggerViewModel() {
        return loggerViewModel;
    }
}
