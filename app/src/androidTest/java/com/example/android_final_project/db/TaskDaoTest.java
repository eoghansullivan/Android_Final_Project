package com.example.android_final_project.db;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;
import com.example.android_final_project.util.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {
    private AppDatabase db;
    private TaskDao taskDao;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        taskDao = db.taskDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertTaskAndReadInList() throws Exception {
        Task task = new Task("Test Task", "Description", Task.TaskType.WORK, "0000-00-00 00:00:00");
        taskDao.insert(task);
        List<Task> allTasks = LiveDataTestUtil.getValue(taskDao.getAllTasks());
        assertThat(allTasks.get(0).getName(), equalTo(task.getName()));
    }
}