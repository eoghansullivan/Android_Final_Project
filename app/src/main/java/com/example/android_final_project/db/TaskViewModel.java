package com.example.android_final_project.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    // ... Other ViewModel code
}