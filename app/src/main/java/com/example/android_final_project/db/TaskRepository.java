package com.example.android_final_project.db;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // Insert a task into the database
    public void insert(Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    // AsyncTask for insert operation
    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskDao asyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update(Task task) {
        new UpdateAsyncTask(taskDao).execute(task);
    }

    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {
        private final TaskDao asyncTaskDao;

        UpdateAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskDao.getAllTasks();
    }
}

