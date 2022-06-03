package com.software.software2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE uid IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM task WHERE completed=1")
    List<Task> getCompleted();

    @Insert
    void insertAll( List<Task> tasks);

    @Delete
    void delete(Task task);
}
