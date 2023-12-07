package com.example.android_final_project.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}