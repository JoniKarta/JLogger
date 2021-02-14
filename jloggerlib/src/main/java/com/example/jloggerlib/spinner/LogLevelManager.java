package com.example.jloggerlib.spinner;

import com.example.jloggerlib.R;
import com.example.jloggerlib.model.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LogLevelManager {

    private volatile static LogLevelManager logLevelManager;

    private static List<LogLevelItem> logLevelItemList;

    private LogLevelManager() {

    }

    public synchronized static LogLevelManager getInstance() {
        if (logLevelManager == null) {
            logLevelManager = new LogLevelManager();
            init();
        }
        return logLevelManager;
    }

    private static void init() {
        logLevelItemList = new ArrayList<>();
        logLevelItemList.addAll(new ArrayList<>(Arrays.asList(
                new LogLevelItem(Logger.DEBUG, R.drawable.debug),
                new LogLevelItem(Logger.ERROR, R.drawable.error),
                new LogLevelItem(Logger.INFO, R.drawable.info),
                new LogLevelItem(Logger.TRACE, R.drawable.trace),
                new LogLevelItem(Logger.WARNING, R.drawable.warning),
                new LogLevelItem(Logger.OTHER, R.drawable.other))));
    }

    public List<LogLevelItem> getLogLevelItemList() {
        return logLevelItemList;
    }
}
