package com.software.software2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "user_id")
    public String userId;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "desc")
    public String desc;
    @ColumnInfo(name = "completed")
    public boolean completed;
    @ColumnInfo(name = "year")
    public int year;
    @ColumnInfo(name = "month")
    public int month;
    @ColumnInfo(name = "day")
    public int day;


    public Task(int uid, String userId, String title, String desc, boolean completed, int year, int month, int day) {
        this.uid=uid;
        this.userId=userId;
        this.title=title;
        this.desc=desc;
        this.completed=completed;
        this.year=year;
        this.month=month;
        this.day=day;

    }

}



