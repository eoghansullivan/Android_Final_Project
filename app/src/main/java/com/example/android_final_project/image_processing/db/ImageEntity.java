package com.example.android_final_project.image_processing.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "images")
public class ImageEntity {
    @PrimaryKey
    @NonNull
    public String path;

    public ImageEntity(@NonNull String path) {
        this.path = path;
    }
}
