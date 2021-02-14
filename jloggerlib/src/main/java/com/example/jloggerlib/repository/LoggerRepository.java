package com.example.jloggerlib.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.jloggerlib.model.Logger;

import java.util.Date;
import java.util.List;

public class LoggerRepository {

    private final LoggerDao loggerDao;

    public LoggerRepository(Application application) {
        LoggerRoomDatabase loggerRoomDatabase = LoggerRoomDatabase.getInstance(application);
        loggerDao = loggerRoomDatabase.loggerDao();
    }


    /**
     * This method run on a background thread and Insert an logger item to the database
     *
     * @param logger
     */
    public void insert(Logger logger) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.insert(logger);
        });
    }

    /**
     * This method run on a background thread and insert multiple logger item to the database
     *
     * @param loggers
     */
    public void insertMany(Logger... loggers) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.insertMany(loggers);
        });
    }


    /**
     * This method return a live-data object which wrap and integer value
     * that count the number of entries in the database
     *
     * @return LiveData<Integer>
     */
    public LiveData<Integer> loggerCount() {
        return loggerDao.loggerCount();
    }


    /**
     * This method run on a background thread and delete specific log from the database
     *
     * @param logger
     */
    public void delete(Logger logger) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.delete(logger);
        });
    }


    /**
     * This method run on a background thread and delete all items from the database
     */
    public void deleteAll() {
        LoggerRoomDatabase.dataWriteExecutorService.execute(loggerDao::deleteAll);
    }

    /**
     * This method return live-data object which wrap list of loggers which correspond
     * to all the logs from the database
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getAllLogs() {
        return loggerDao.getAllLogs();
    }

    /**
     * This method return live-data object which wrap list of loggers which correspond
     * to all the logs from the database which has a given tag name
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getAllLogsByTagName(String tag) {
        return loggerDao.getAllLogsByTagName(tag);
    }

    /**
     * This method return live-data object which wrap list of loggers which correspond
     * to all the logs from the database which has a date greater than the given date
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getAllLogsByDateGreaterThan(Date start) {
        return loggerDao.getAllLogsByDateGreaterThan(start);
    }

    /**
     * This method return live-data object which wrap list of loggers which correspond
     * to all the logs from the database which has a given message
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getAllLogsByMessageLike(String filterPattern) {
        return loggerDao.getAllLogsByMessageLike(filterPattern);
    }

    /**
     * This method return live-data object which wrap list of loggers which correspond
     * to all the logs from the database which has a given tag name, sorting attribute and pattern
     * to find by
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getAllByTagAndFilterValueLike(String direction, String filterPattern, String tag) {
        return loggerDao.getAllByTagAndFilterValueLike(direction, filterPattern, tag);
    }
}