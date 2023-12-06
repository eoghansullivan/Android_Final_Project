package com.example.android_final_project;

import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.Looper;
import android.view.View;
import android.view.ViewParent;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android_final_project.R;
import com.example.android_final_project.TestOutputActivity;
import com.example.android_final_project.application.MainApplication;
import com.example.android_final_project.db.AppDatabase;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.db.TaskDao;
import com.example.android_final_project.gui.TaskListFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
        Task mockTask = new Task("Mock Task", "Description", Task.TaskType.PERSONAL, "2021-01-01");
        taskDao.insert(mockTask);

        // Set the in-memory database instance in your application class
        MainApplication.getInstance().setDatabase(db);
    }

    @Before
    public void launchFragment() {
        // Launch the MainActivity
        ActivityScenario.launch(TestOutputActivity.class);

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