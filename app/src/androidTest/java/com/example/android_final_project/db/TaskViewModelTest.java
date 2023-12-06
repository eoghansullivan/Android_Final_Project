package com.example.android_final_project.db;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android_final_project.util.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TaskViewModelTest {

    private TaskRepository repository;
    private TaskViewModel viewModel;

    @Before
    public void setUp() {
        // Initialize the repository with a mock DAO and then the ViewModel
        repository = new TaskRepository();
        //viewModel = new TaskViewModel(repository);
    }

    @Test
    public void testGetAllTasks() throws InterruptedException {
        // Mock the DAO to return expected LiveData
        //when(repository.getAllTasks()).thenReturn(LiveDataTestUtil.getValue(...));

        // Assert that ViewModel returns expected data
        assertEquals("Expected data", LiveDataTestUtil.getValue(viewModel.getAllTasks()));
    }
}