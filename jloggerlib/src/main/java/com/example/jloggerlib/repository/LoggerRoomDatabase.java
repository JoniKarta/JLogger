package com.example.jloggerlib.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.jloggerlib.converters.DateConverter;
import com.example.jloggerlib.model.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@TypeConverters({DateConverter.class})
@Database(entities = {Logger.class}, version = 1,exportSchema = false)
public abstract class LoggerRoomDatabase extends RoomDatabase {

    public abstract LoggerDao loggerDao();

    private static volatile LoggerRoomDatabase loggerRoomDatabase;
    private static final Lock lock = new ReentrantLock();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dataWriteExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static LoggerRoomDatabase getInstance(final Context context){
        // double check locking for improving performance
        if(loggerRoomDatabase == null){
            try{
                lock.lock();
                if(loggerRoomDatabase == null){
                    loggerRoomDatabase = Room.databaseBuilder(
                            context.getApplicationContext(),
                            LoggerRoomDatabase.class, "logger_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }finally{
                lock.unlock();
            }
        }
        return loggerRoomDatabase;
    }


}
