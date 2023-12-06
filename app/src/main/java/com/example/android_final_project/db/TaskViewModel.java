package com.example.android_final_project.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application, TaskRepository repository) {
        super(application);
       // repository = new TaskRepository();
        this.repository = repository;
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    // ... Other ViewModel code
}