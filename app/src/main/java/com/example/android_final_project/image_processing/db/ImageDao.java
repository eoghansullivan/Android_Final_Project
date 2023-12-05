package com.example.android_final_project.image_processing.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insertImage(ImageEntity image);

    @Query("SELECT * FROM images")
    List<ImageEntity> getAllImages();
}
