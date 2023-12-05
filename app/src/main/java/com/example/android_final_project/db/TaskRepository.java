package com.example.android_final_project.db;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android_final_project.application.MainApplication;

public class TaskRepository {

    private TaskDao taskDao;

    public TaskRepository() {
        AppDatabase db = MainApplication.getInstance().getDatabase();
        taskDao = db.taskDao();
    }

    // Insert a task into the database
    public void insert(Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    // AsyncTask for insert operation
    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao asyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    // You can also add methods for update, delete, and query operations here
}

