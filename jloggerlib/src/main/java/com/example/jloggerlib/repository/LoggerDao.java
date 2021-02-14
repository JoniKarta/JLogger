package com.example.jloggerlib.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jloggerlib.model.Logger;
import com.example.jloggerlib.utility.Direction;

import java.util.Date;
import java.util.List;

@Dao
public interface LoggerDao {

    @Insert()
    void insert(Logger logger);

    @Insert()
    void insertMany(Logger... loggers);

    @Query("SELECT COUNT(*) from logger_table")
    LiveData<Integer> loggerCount();

    @Query("DELETE FROM logger_table")
    void deleteAll();

    @Delete
    void delete(Logger logger);

    @Query("SELECT * FROM logger_table ORDER BY date DESC")
    LiveData<List<Logger>> getAllLogs();

    @Query("SELECT * FROM logger_table WHERE date > :start")
    LiveData<List<Logger>> getAllLogsByDateGreaterThan(Date start);

    @Query("SELECT * FROM logger_table WHERE tag LIKE :tag")
    LiveData<List<Logger>> getAllLogsByTagName(String tag);

    @Query("SELECT * FROM logger_table WHERE msg LIKE '%' || :filterValue || '%' ORDER BY date DESC")
    LiveData<List<Logger>> getAllLogsByMessageLike(String filterValue);

    @Query("SELECT * FROM logger_table WHERE tag = :tag AND msg LIKE '%' || :filterValue || '%' ORDER BY msg ASC")
    LiveData<List<Logger>> getAllByTagAndFilterValueLikeAsc(String filterValue, String tag);

    @Query("SELECT * FROM logger_table WHERE tag = :tag AND msg LIKE '%' || :filterValue || '%' ORDER BY msg DESC")
    LiveData<List<Logger>> getAllByTagAndFilterValueLikeDesc(String filterValue, String tag);

    default LiveData<List<Logger>> getAllByTagAndFilterValueLike(String direction, String filterValue, String tag) {
        if (direction.equals(Direction.ASC.name())) {
            return getAllByTagAndFilterValueLikeAsc(filterValue, tag);
        } else {
            return getAllByTagAndFilterValueLikeDesc(filterValue, tag);
        }
    }
}

