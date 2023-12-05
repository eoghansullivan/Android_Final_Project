package com.example.android_final_project.application;

import android.app.Application;

import androidx.room.Room;

import com.example.android_final_project.db.AppDatabase;

public class MainApplication extends Application {

    private static MainApplication instance;
    private AppDatabase database;

    public static final String LOG_HEADER = "A00287845";
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_database")
                .fallbackToDestructiveMigration() // Handle migrations
                .build();
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}