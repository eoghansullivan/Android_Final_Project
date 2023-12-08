package com.example.android_final_project;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android_final_project.application.MainApplication;
import com.example.android_final_project.db.AppDatabase;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.db.TaskDao;
import com.example.android_final_project.gui.TaskListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TaskListFragmentTest {

        @Before
    public void setUp() {
        // Get a reference to the in-memory database
        AppDatabase db = Room.inMemoryDatabaseBuilder(
                        InstrumentationRegistry.getInstrumentation().getTargetContext(),
                        AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        // Pre-populate the database with mock data
        TaskDao taskDao = db.taskDao();
        Task mockTask = new Task("Mock Task", "Description", Task.TaskType.PERSONAL, "2021-01-01", true);
        taskDao.insert(mockTask);

        // Set the in-memory database instance in your application class
        MainApplication.getInstance().setDatabase(db);
    }

    @Before
    public void launchFragment() {
        // Launch the MainActivity
        ActivityScenario.launch(MainActivityFrame.class);

        // Initialize the fragment scenario for the TaskListFragment
        FragmentScenario.launchInContainer(TaskListFragment.class);
    }

    @Test
    public void testRecyclerViewDisplay() {
        // Verify that the RecyclerView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.tasksRecyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


    // Add more tests as needed for your specific functionality


}