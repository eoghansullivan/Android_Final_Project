package com.example.android_final_project.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);
}