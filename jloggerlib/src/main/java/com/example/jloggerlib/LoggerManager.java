package com.example.jloggerlib;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.jloggerlib.viewmodel.LoggerViewModel;


public final class LoggerManager {

    private static LoggerManager loggerManager;
    private final LoggerViewModel loggerViewModel;

    private LoggerManager(Application application) {
        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(LoggerViewModel.class);
    }

    public synchronized static LoggerManager getInstance(Application application) {
        if (loggerManager == null) {
            loggerManager = new LoggerManager(application);
        }
        return loggerManager;
    }

    public LoggerViewModel getLoggerViewModel() {
        return loggerViewModel;
    }
}
