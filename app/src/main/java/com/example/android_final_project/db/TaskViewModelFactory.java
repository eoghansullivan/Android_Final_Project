package com.example.android_final_project.db;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TaskViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public TaskViewModelFactory(Application application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}