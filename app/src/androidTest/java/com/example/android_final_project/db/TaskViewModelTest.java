package com.example.android_final_project.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskViewModelTest {

    // TODO finish
//
//    private TaskViewModel viewModel;
//    private TaskRepository repository;
//    private TaskDao mockTaskDao;
//    private LiveData<List<Task>> mockTaskData;
//
//    @Before
//    public void setUp() {
//        mockTaskDao = Mockito.mock(TaskDao.class);
//        repository = new TaskRepository(mockTaskDao);
//        viewModel = new TaskViewModel(Mockito.mock(Application.class), repository);
//
//        // Create mock data for testing
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Task 1", "Description 1", Task.TaskType.PERSONAL, "2021-01-01"));
//        tasks.add(new Task("Task 2", "Description 2", Task.TaskType.WORK, "2021-01-02"));
//        mockTaskData = new MutableLiveData<>(tasks);
//
//        // Mock the behavior of getAllTasks
//        Mockito.when(mockTaskDao.getAllTasks()).thenReturn(mockTaskData);
//    }
//
//    @Test
//    public void testGetAllTasks() {
//        // When ViewModel's getAllTasks is called, it should return the mock data
//        viewModel.getAllTasks().observeForever(new Observer<List<Task>>() {
//            @Override
//            public void onChanged(List<Task> tasks) {
//                // Assert that the returned data matches the mock data
//                assertNotNull(tasks);
//                assertEquals(2, tasks.size());
//                assertEquals("Task 1", tasks.get(0).getName());
//                assertEquals("Task 2", tasks.get(1).getName());
//            }
//        });
//
//        // Verify that TaskDao's getAllTasks was called
//        Mockito.verify(mockTaskDao).getAllTasks();
//    }
}