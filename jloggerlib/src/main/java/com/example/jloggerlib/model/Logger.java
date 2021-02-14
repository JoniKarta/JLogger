package com.example.jloggerlib.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "logger_table")
public class Logger {

    public static final String TRACE = "TRACE";

    public static final String DEBUG = "DEBUG";

    public static final String INFO = "INFO";

    public static final String ERROR = "ERROR";

    public static final String WARNING = "WARNING";

    public static final String OTHER = "OTHER";

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "tag")
    private String tag;

    @ColumnInfo(name = "msg")
    private String msg;

    private Date date;

    public Logger(String tag, String msg, Date date) {
        this.tag = tag;
        this.msg = msg;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Logger{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
